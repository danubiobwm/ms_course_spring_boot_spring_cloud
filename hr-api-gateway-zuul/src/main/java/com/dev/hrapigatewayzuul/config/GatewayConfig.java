package com.dev.hrapigatewayzuul.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("hr-worker", r -> r.path("/hr-worker/**")
                        .uri("lb://hr-worker"))
                .route("hr-payroll", r -> r.path("/hr-payroll/**")
                        .uri("lb://hr-payroll"))
                .build();
    }
}
