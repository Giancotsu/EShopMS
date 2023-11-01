package com.eshop.price.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
public class ErrorObj {

    private int code;
    private String status;
    private String message;
    private String details;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;

    public ErrorObj() {
    }

    public ErrorObj(HttpStatus httpStatus, String message, String details) {
        this.code = httpStatus.value();
        this.status = httpStatus.name();
        this.message = message;
        this.details = details;
        this.timestamp = new Date();
    }
}
