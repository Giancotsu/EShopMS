package com.eshop.eshop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ErrorObj> handleItemNotFoundException(ItemNotFoundException exception, WebRequest request){

        ErrorObj errorObj = new ErrorObj();

        errorObj.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObj.setMessage(exception.getMessage());
        errorObj.setTimestamp(new Date());

        return new ResponseEntity<>(errorObj, HttpStatus.NOT_FOUND);
    }
}
