package com.eshop.price.controllers;

import com.eshop.price.dtos.PriceDto;
import com.eshop.price.service.impl.PriceServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/price")
public class PriceController {

    private final PriceServiceImpl priceService;

    public PriceController(PriceServiceImpl priceService) {
        this.priceService = priceService;
    }

    @GetMapping(value = "/item/{itemId}")
    public ResponseEntity<PriceDto> getPriceByItem(@PathVariable("itemId") long itemId){
        return new ResponseEntity<>(priceService.getPriceByItem(itemId), HttpStatus.OK);
    }
}
