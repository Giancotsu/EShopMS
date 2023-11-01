package com.eshop.price.exceptions;

public class SaleNotFoundException extends RuntimeException{

    public SaleNotFoundException(String message) {
        super(message);
    }
}
