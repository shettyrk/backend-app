package com.example.api_gateway.constants;

public final class ServiceRoutes {

    private ServiceRoutes() {}

    public static final String AUTH_SERVICE = "lb://auth-service";
    public static final String PRODUCT_SERVICE = "lb://product-service";
    public static final String ORDER_SERVICE = "lb://order-service";
    public static final String INVENTORY_SERVICE = "lb://inventory-service";
    public static final String NOTIFICATION_SERVICE = "lb://notification-service";

    // Base paths (should match each service’s controller mappings)
    public static final String AUTH_PATH = "/api/auth/**";
    public static final String PRODUCT_PATH = "/api/products/**";
    public static final String ORDER_PATH = "/api/orders/**";
    public static final String INVENTORY_PATH = "/api/inventory/**";
    public static final String NOTIFICATION_PATH = "/api/notifications/**";
}

