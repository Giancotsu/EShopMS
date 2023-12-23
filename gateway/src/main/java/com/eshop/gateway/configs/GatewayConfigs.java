package com.eshop.gateway.configs;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfigs {

    private final AuthenticationFilter filter;

    public GatewayConfigs(AuthenticationFilter filter) {
        this.filter = filter;
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("esItems",r -> r.path("/api/item/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://ESHOPITEMS")
                )
                .route("esItemsCategory",r -> r.path("/api/category/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://ESHOPITEMS")
                )
                .route("esPrice",r -> r.path("/api/price/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://ESHOPPRICE")
                )
                .route("esOrder",r -> r.path("/order/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://ESHOPORDER")
                )
                .route("esInventory",r -> r.path("/inventory/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://ESHOPINVENTORY")
                )
                .route("esUser",r -> r.path("/users/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://ESHOPUSER")
                )
                .route("esAuth",r -> r.path("/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://authentication-service")
                )
                .route("esEureka",r -> r.path("/eureka")
                        .filters(f -> f.filter(filter).setPath("/"))
                        .uri("http://localhost:8761")
                )
                .route("esEureka-static",r -> r.path("/eureka/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:8761")
                )
                .build();
    }
}
