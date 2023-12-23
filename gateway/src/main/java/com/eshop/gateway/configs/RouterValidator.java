package com.eshop.gateway.configs;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class RouterValidator {

    public static final List<String> freeEndpoints = List.of(
            "auth/register",
            "auth/login"
    );

    public static final List<String> adminEndpoints = List.of(
            "admin/test"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> freeEndpoints.stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

    public Predicate<ServerHttpRequest> needAdmin =
            request -> adminEndpoints.stream().anyMatch(uri -> request.getURI().getPath().contains(uri));
}
