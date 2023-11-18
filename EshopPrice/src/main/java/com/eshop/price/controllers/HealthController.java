package com.eshop.price.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/price-service")
public class HealthController {

    @GetMapping(value = "is-on")
    public ResponseEntity<Boolean> amIOn(){
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
