package com.werfen.gateway;

import com.werfen.gateway.tenant.TenantDTO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RequiredArgsConstructor
@Component
public class GatewayConfig {

    private Log logger = LogFactory.getLog(this.getClass());

    private static final String TENANT_HEADER = "X-TenantID";

    private final WebClient.Builder client;

    @Value("${app.k8s:true}")
    private boolean k8s;

    @Bean
    RouteLocator gateway(RouteLocatorBuilder rlb) {
        return rlb.routes()
                .route(routeSpec -> routeSpec.path("/swagger")
                        .uri(getURIForLaboratoryService()+"/swagger-ui.html"))
                .route(routeSpec -> routeSpec.path("/inventory/**")
                        .filters(spec -> removePathPrefix(spec, "/inventory").filters(this::changeTenantFromIdToSchema))
                        .uri(getURIForInventoryService()))
                .route(routeSpec -> routeSpec.path("/laboratory/**")
                        .filters(spec -> removePathPrefix(spec, "/laboratory").filters(this::changeTenantFromIdToSchema))
                        .uri(getURIForLaboratoryService()))
                .route(routeSpec -> routeSpec.path("/api/**")
                        .filters(f -> f.filter(this::changeTenantFromIdToSchema))
                        .uri(getURIForLaboratoryService()))
                .route(routeSpec -> routeSpec.path("/**")
                        .uri(getURIForLaboratoryService()))
                .build();
    }

    private GatewayFilterSpec removePathPrefix(GatewayFilterSpec spec, String prefix) {
        logger.info("Removing prefix: "+prefix);
        return spec.rewritePath(prefix, "");
    }

    private Mono<Void> changeTenantFromIdToSchema(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("Getting the tenant schema for tenant id: "+ exchange.getRequest().getHeaders().getFirst(TENANT_HEADER));
        return client.baseUrl(getURIForTenantService()).build().get()
                .uri("/tenants/" + "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                .retrieve()
                .bodyToMono(TenantDTO.class).map(TenantDTO::getSchema)
                .map(id -> {
                    System.out.println(id);
                    exchange.getRequest().mutate().headers(h -> h.set(TENANT_HEADER, exchange.getRequest().getHeaders().getFirst(TENANT_HEADER)));
                    return exchange;
                })
                .flatMap(chain::filter);

    }

    private Mono<Void> changeTenantFromIdToSchemaGood(ServerWebExchange exchange, GatewayFilterChain chain) {
        return client.baseUrl(getURIForTenantService()).build().get()
                .uri("/tenants/" + exchange.getRequest().getHeaders().getFirst(TENANT_HEADER))
                .retrieve()
                .bodyToMono(TenantDTO.class).map(TenantDTO::getSchema)
                .map(id -> {
                    exchange.getRequest().mutate().headers(h -> h.set(TENANT_HEADER, id));
                    return exchange;
                })
                .flatMap(chain::filter);
    }

    private String getURIForInventoryService() {
        if (k8s) {
            return "http://inventory-service.default.svc.cluster.local:8885";
        } else {
            return "lb://inventory-service";
        }
    }

    private String getURIForLaboratoryService() {
        if (k8s) {
            return "http://laboratory-service.default.svc.cluster.local:8886";
        } else {
            return "lb://laboratory-service";
        }
    }

    private String getURIForTenantService() {
        if (k8s) {
            return "http://tenant-service.default.svc.cluster.local:8887";
        } else {
            return "lb://tenant-service";
        }
    }
}
