package com.eshop.price.controllers;

import com.eshop.price.dtos.ItemClientRequestPriceCategory;
import com.eshop.price.dtos.PriceDto;
import com.eshop.price.dtos.SaleDto;
import com.eshop.price.service.impl.PriceServiceImpl;
import org.springframework.http.HttpStatus;
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

    @PostMapping(value = "/set/enhanced")
    public ResponseEntity<PriceDto> setItemPriceAndCategories(@RequestBody ItemClientRequestPriceCategory requestBody){
        return new ResponseEntity<>(priceService.setItemPriceAndCategories(requestBody), HttpStatus.OK);
    }

    @PostMapping(value = "/{priceId}/sale")
    public ResponseEntity<PriceDto> setPriceSaleSingleByPriceId(@PathVariable("priceId") long priceId, @RequestBody SaleDto saleDto){
        return new ResponseEntity<>(priceService.setPriceSaleSingleByPriceId(priceId, saleDto), HttpStatus.OK);
    }

    @PostMapping(value = "/item/{itemId}/sale")
    public ResponseEntity<PriceDto> setPriceSaleSingleByItemId(@PathVariable("itemId") long itemId, @RequestBody SaleDto saleDto){
        return new ResponseEntity<>(priceService.setPriceSaleSingleByItemId(itemId, saleDto), HttpStatus.OK);
    }

    @PostMapping(value = "/item/{itemId}/remove/sale")
    public ResponseEntity<String> removePriceSaleSingleByItemId(@PathVariable("itemId") long itemId, @RequestBody SaleDto saleDto){
        return new ResponseEntity<>(priceService.removeSaleSingleByItemId(itemId, saleDto), HttpStatus.OK);
    }
}
