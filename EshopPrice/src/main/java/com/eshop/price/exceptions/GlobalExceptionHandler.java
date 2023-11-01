package com.eshop.price.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<ErrorObj> handlePriceNotFoundException(PriceNotFoundException exception, WebRequest request){

        ErrorObj errorObj = new ErrorObj(
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorObj, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SaleNotFoundException.class)
    public ResponseEntity<ErrorObj> handleSaleNotFoundException(SaleNotFoundException exception, WebRequest request){

        ErrorObj errorObj = new ErrorObj(
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorObj, HttpStatus.NOT_FOUND);
    }
}
