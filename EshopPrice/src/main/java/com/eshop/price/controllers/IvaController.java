package com.eshop.price.controllers;

import com.eshop.price.configs.ApplicationConfigs;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "iva")
public class IvaController {

    private final ApplicationConfigs appConf;

    public IvaController(ApplicationConfigs appConf) {
        this.appConf = appConf;
    }

    @GetMapping
    public ResponseEntity<Integer> getIva(){
        return new ResponseEntity<>(appConf.getIva(), HttpStatus.OK);
    }
}
