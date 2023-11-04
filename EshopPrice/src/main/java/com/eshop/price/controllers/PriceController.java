package com.eshop.price.controllers;

import com.eshop.price.dtos.ItemClientRequestPriceCategory;
import com.eshop.price.dtos.PriceDto;
import com.eshop.price.dtos.SaleDto;
import com.eshop.price.service.PriceService;
import com.eshop.price.service.impl.PriceServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/price")
public class PriceController {

    private final PriceService priceService;

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

    @GetMapping(value = "/sale/{saleId}/category/{categoryId}")
    public ResponseEntity<List<PriceDto>> setPriceSaleByCategory(@PathVariable("saleId") long saleId, @PathVariable("categoryId") long categoryId){
        return new ResponseEntity<>(priceService.setPriceSaleByCategory(saleId, categoryId), HttpStatus.OK);
    }

    @GetMapping(value = "/sale/{saleId}/all")
    public ResponseEntity<List<PriceDto>> setPriceSaleAll(@PathVariable("saleId") long saleId){
        return new ResponseEntity<>(priceService.setPriceSaleAll(saleId), HttpStatus.OK);
    }

    @PostMapping(value = "/item/{itemId}/remove/sale")
    public ResponseEntity<String> removePriceSaleSingleByItemId(@PathVariable("itemId") long itemId, @RequestBody SaleDto saleDto){
        return new ResponseEntity<>(priceService.removeSaleSingleByItemId(itemId, saleDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/remove/sale/{saleId}")
    public ResponseEntity<String> removeSale(@PathVariable("saleId") long saleId){
        return new ResponseEntity<>(priceService.removeSale(saleId), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{itemId}/remove/sale/{saleId}")
    public ResponseEntity<String> removeSaleFromSinglePrice(@PathVariable("itemId") long itemId, @PathVariable("saleId") long saleId){
        return new ResponseEntity<>(priceService.removeSaleFromSinglePrice(itemId, saleId), HttpStatus.OK);
    }


}
