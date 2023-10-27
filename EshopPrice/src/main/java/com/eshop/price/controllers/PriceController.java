package com.eshop.price.controllers;

import com.eshop.price.dtos.PriceDto;
import com.eshop.price.service.impl.PriceServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/api/price")
public class PriceController {

    private final PriceServiceImpl priceService;

    public PriceController(PriceServiceImpl priceService) {
        this.priceService = priceService;
    }

    @GetMapping(value = "/item/{itemId}")
    public ResponseEntity<BigDecimal> getPriceByItem(@PathVariable("itemId") long itemId){
        return new ResponseEntity<>(priceService.getPriceByItem(itemId), HttpStatus.OK);
    }

    @PostMapping(value = "/set/{itemId}")
    public ResponseEntity<PriceDto> setItemPrice(@PathVariable("itemId") long itemId, @RequestBody BigDecimal price){
        return new ResponseEntity<>(priceService.setPriceToItem(itemId, price), HttpStatus.OK);
    }
}
