package com.eshop.price.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "EshopItems")
public interface ItemClient {

    @GetMapping(value = "/api/item/clear-cache/{itemId}")
    ResponseEntity<String> clearItemsCache(@PathVariable("itemId") long itemId);
}
