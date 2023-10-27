package com.eshop.price.controllers;

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
    public ResponseEntity<String> setItemPrice(@PathVariable("itemId") long itemId, @RequestBody BigDecimal price){
        priceService.setPriceToItem(itemId, price);
        String response = "The price of the item with ID: " + itemId + "is: " + price;
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
