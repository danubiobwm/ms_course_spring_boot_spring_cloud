package com.dev.hrapigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    RouteLocator customRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                // ✅ ROTA OAUTH2 - ESTAVA FALTANDO!
                .route("oauth-server", r -> r.path("/oauth2/**", "/login/**", "/.well-known/**", "/userinfo")
                    .filters(f -> f.preserveHostHeader())
                    .uri("lb://hr-oauth"))

                // ✅ HR-OAUTH (se quiser prefixo também)
                .route("hr-oauth-prefix", r -> r.path("/hr-oauth/**")
                    .filters(f -> f.rewritePath("/hr-oauth/(?<segment>.*)", "/${segment}"))
                    .uri("lb://hr-oauth"))

                // ✅ HR-WORKER
                .route("hr-worker", r -> r.path("/hr-worker/**")
                    .filters(f -> f.rewritePath("/hr-worker/(?<segment>.*)", "/${segment}"))
                    .uri("lb://hr-worker"))

                // ✅ HR-PAYROLL
                .route("hr-payroll", r -> r.path("/hr-payroll/**")
                    .filters(f -> f.rewritePath("/hr-payroll/(?<segment>.*)", "/${segment}"))
                    .uri("lb://hr-payroll"))

                // ✅ HR-USER
                .route("hr-user", r -> r.path("/users/**")
                    .uri("lb://hr-user"))
                .build();
    }
}