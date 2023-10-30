package com.eshop.price.controllers;

import com.eshop.price.dtos.SaleDto;
import com.eshop.price.service.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/sale")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping(value = "/new")
    public ResponseEntity<SaleDto> createSale(@RequestBody SaleDto saleDto){
        return new ResponseEntity<>(saleService.createSale(saleDto), HttpStatus.CREATED);
    }
}
