package com.example.api_gateway.config;

import com.example.api_gateway.constants.ServiceRoutes;
import org.springframework.cloud.gateway.route.RouteLocator ;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Defines all microservice routes for the API Gateway.
 * This approach is production-friendly and avoids hardcoding routes in YAML.
 */
@Configuration
public class GatewayRoutesConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()

                // 🔐 Auth Service
                .route("auth-service", r -> r.path(ServiceRoutes.AUTH_PATH)
                        .filters(f -> f.stripPrefix(1))
                        .uri(ServiceRoutes.AUTH_SERVICE))

                // 🕯️ Product Service
                .route("product-service", r -> r.path(ServiceRoutes.PRODUCT_PATH)
                        .filters(f -> f.stripPrefix(1))
                        .uri(ServiceRoutes.PRODUCT_SERVICE))

                // 🛒 Order Service
                .route("order-service", r -> r.path(ServiceRoutes.ORDER_PATH)
                        .filters(f -> f.stripPrefix(1))
                        .uri(ServiceRoutes.ORDER_SERVICE))

                // 📦 Inventory Service
                .route("inventory-service", r -> r.path(ServiceRoutes.INVENTORY_PATH)
                        .filters(f -> f.stripPrefix(1))
                        .uri(ServiceRoutes.INVENTORY_SERVICE))

                // ✉️ Notification Service
                .route("notification-service", r -> r.path(ServiceRoutes.NOTIFICATION_PATH)
                        .filters(f -> f.stripPrefix(1))
                        .uri(ServiceRoutes.NOTIFICATION_SERVICE))

                .build();
    }
}

