package com.eshop.eshop.controllers;

import com.eshop.eshop.models.ItemEntity;
import com.eshop.eshop.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/item/")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<ItemEntity>> getAllItems(){

    }

    @GetMapping(value = "${id}")
    public ResponseEntity<ItemEntity> getItemById(@PathVariable long id){

    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ItemEntity> createItem(@RequestBody ItemEntity item){

    }

    @PutMapping(value = "${id}/update")
    public ResponseEntity<ItemEntity> updateItem(@RequestBody ItemEntity item, @PathVariable long id){


    }

    @DeleteMapping(value = "${id}/delete")
    public ResponseEntity<String> deleteItem(@PathVariable long id){


    }
}
