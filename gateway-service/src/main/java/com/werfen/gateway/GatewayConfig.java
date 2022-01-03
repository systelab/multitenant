package com.werfen.gateway;

import com.werfen.gateway.tenant.TenantDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class GatewayConfig {

    private static final String TENANT_HEADER = "X-TenantID";

    private final WebClient.Builder client;

    @Bean
    RouteLocator gateway(RouteLocatorBuilder rlb) {
        return rlb.routes()
                .route(routeSpec -> routeSpec.path("/inventory/**")
                        .filters(spec -> removePathPrefix(spec, "/inventory").filters(this::changeTenantFromIdToSchema))
                        .uri("lb://inventory-service"))
                .route(routeSpec -> routeSpec.path("/laboratory/**")
                        .filters(spec -> removePathPrefix(spec, "/laboratory").filters(this::changeTenantFromIdToSchema))
                        .uri("lb://laboratory-service"))
                .route(routeSpec -> routeSpec.path("/api/**")
                        .filters(f -> f.filter(this::changeTenantFromIdToSchema))
                        .uri("lb://laboratory-service"))
                .route(routeSpec -> routeSpec.path("/**")
                        .uri("lb://laboratory-service"))
                .build();
    }

    private GatewayFilterSpec removePathPrefix(GatewayFilterSpec spec, String prefix) {
        return spec.rewritePath(prefix, "");
    }

    private Mono<Void> changeTenantFromIdToSchema(ServerWebExchange exchange, GatewayFilterChain chain) {
        return client.baseUrl("lb://tenant-service").build().get()
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
        return client.baseUrl("lb://tenant-service").build().get()
                .uri("/tenants/" + exchange.getRequest().getHeaders().getFirst(TENANT_HEADER))
                .retrieve()
                .bodyToMono(TenantDTO.class).map(TenantDTO::getSchema)
                .map(id -> {
                    exchange.getRequest().mutate().headers(h -> h.set(TENANT_HEADER, id));
                    return exchange;
                })
                .flatMap(chain::filter);
    }
}
