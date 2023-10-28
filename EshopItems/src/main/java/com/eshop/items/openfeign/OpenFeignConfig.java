package com.eshop.items.openfeign;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class OpenFeignConfig {

    @Bean
    ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }
}
