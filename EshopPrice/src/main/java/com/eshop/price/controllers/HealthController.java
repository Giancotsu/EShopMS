package com.eshop.price.controllers;

import com.eshop.price.configs.ApplicationConfigs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/price-service")
public class HealthController {

    @Autowired
    private ApplicationConfigs applicationConfigs;

    @GetMapping(value = "is-on")
    public ResponseEntity<Boolean> amIOn(){
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping(value = "x")
    public ResponseEntity<Integer> getXValue(){
        return new ResponseEntity<>(applicationConfigs.getX(), HttpStatus.OK);
    }
}
