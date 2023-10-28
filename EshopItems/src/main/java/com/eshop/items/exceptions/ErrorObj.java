package com.eshop.items.exceptions;

import lombok.Data;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

@Data
public class ErrorObj {

    @JsonProperty(value = "code")
    private int code;

    @JsonProperty(value = "status")
    private String status;

    @JsonProperty(value = "message")
    private String message;

    @JsonProperty(value = "details")
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
