package com.eshop.price.controllers;

import com.eshop.price.dtos.PriceDto;
import com.eshop.price.service.impl.PriceServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    @PostMapping(value = "/set/enhanced/{itemId}")
    public ResponseEntity<PriceDto> setItemPriceAndCategories(@PathVariable("itemId") long itemId, @RequestBody Map<String, Set<String>> requestBody){

        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(requestBody.get("price").stream().findFirst().get()));
        Set<Long> itemCategories = requestBody.get("categories").stream().map(Long::parseLong).collect(Collectors.toSet());

        System.out.println(price);
        System.out.println(itemCategories);

        return new ResponseEntity<>(priceService.setPriceToItem(itemId, price), HttpStatus.OK);
    }
}
