package com.eshop.items.openfeign;

import com.eshop.items.exceptions.BadRequestException;
import com.eshop.items.exceptions.PriceNotFoundException;
import com.eshop.items.exceptions.PriceServiceNotAvailableException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

@Component
public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        System.err.println(response.status());
        return switch (response.status()) {
            case 400 -> new BadRequestException();
            case 404 -> new PriceNotFoundException("Price not found");
            case 500 -> new PriceServiceNotAvailableException("Price Api is unavailable");
            default -> new Exception("Exception while getting price");
        };
    }
}
