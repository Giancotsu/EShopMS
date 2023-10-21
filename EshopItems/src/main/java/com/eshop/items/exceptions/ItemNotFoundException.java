package com.eshop.items.exceptions;

public class ItemNotFoundException extends RuntimeException{
    static final long serialVersionUID = 1L;

    public ItemNotFoundException(String message){
        super(message);
    }
}
