package com.eshop.items.exceptions;

public class PriceServiceNotAvailableException extends RuntimeException {

    public PriceServiceNotAvailableException(String message) {
        super(message);
    }
}