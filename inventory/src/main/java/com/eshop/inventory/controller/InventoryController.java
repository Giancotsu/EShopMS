package com.eshop.inventory.controller;

import com.eshop.inventory.dto.CheckCartRequest;
import com.eshop.inventory.entity.InventoryEntity;
import com.eshop.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{isbn}/{quantity}")
    public ResponseEntity<Boolean> isInStock(@PathVariable("isbn") String isbn, @PathVariable("quantity") Integer quantity){
        return new ResponseEntity<>(inventoryService.isInStock(isbn, quantity), HttpStatus.OK);
    }

    @PostMapping("/cart-check")
    public ResponseEntity<List<CheckCartRequest>> checkCart(@RequestBody List<CheckCartRequest> cartRequests){
        return new ResponseEntity<>(inventoryService.checkCart(cartRequests), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> updateInventory(@RequestBody InventoryEntity inventory){
        return new ResponseEntity<>(inventoryService.updateInventory(inventory), HttpStatus.CREATED);
    }
}
