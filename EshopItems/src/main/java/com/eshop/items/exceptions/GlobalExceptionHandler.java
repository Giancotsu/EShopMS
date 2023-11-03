package com.eshop.items.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ErrorObj> handleItemNotFoundException(ItemNotFoundException exception, WebRequest request){

        ErrorObj errorObj = new ErrorObj();

        errorObj.setCode(HttpStatus.NOT_FOUND.value());
        errorObj.setStatus(HttpStatus.NOT_FOUND.name());
        errorObj.setMessage(exception.getMessage());
        errorObj.setDetails(request.getDescription(false));
        errorObj.setTimestamp(new Date());

        return new ResponseEntity<>(errorObj, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({PriceServiceNotAvailableException.class})
    public ResponseEntity<ErrorObj> handlePriceServiceNotAvailableException(PriceServiceNotAvailableException exception, WebRequest request) {

        return new ResponseEntity<>(new ErrorObj(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), request.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({PriceNotFoundException.class})
    public ResponseEntity<ErrorObj> handlePriceNotFoundException(PriceNotFoundException exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorObj(
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                request.getDescription(false)),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({CategoryNotFoundException.class})
    public ResponseEntity<ErrorObj> handleCategoryNotFoundException(CategoryNotFoundException exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorObj(
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                request.getDescription(false)),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObj> handleGenericException(Exception exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorObj(
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                request.getDescription(false)),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
