package com.werfen.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class GatewayConfig {

    @Bean
    RouteLocator gateway(RouteLocatorBuilder rlb) {
        return rlb.routes()
                .route(routeSpec -> routeSpec.path("/s1")
                        .filters(gatewayFilterSpec -> gatewayFilterSpec.setPath("/swagger-ui.html"))
                        .uri("http://localhost:8081"))
                .route(routeSpec -> routeSpec.path("/s2")
                        .filters(gatewayFilterSpec -> gatewayFilterSpec.setPath("/swagger-ui.html"))
                        .uri("http://localhost:8082"))
                .route(routeSpec -> routeSpec.path("/**")
                        .filters(f -> f.filter((exchange, chain) -> {
                            ServerHttpRequest request = exchange.getRequest();
                            String tenantId = request.getHeaders().getFirst("X-TenantID");
                            ServerHttpRequest mutatedRequest =request.mutate().header("X-TenantID", getTenantSchemaById(tenantId)).build();
                            ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();
                            return chain.filter(mutatedExchange);
                        }))
                        .uri("lb://laboratory-service"))
                .build();
    }

    private String getTenantSchemaById(String tenantId) {
        return tenantId;
    }
}
