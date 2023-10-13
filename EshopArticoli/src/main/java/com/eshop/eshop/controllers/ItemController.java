package com.eshop.eshop.controllers;

import com.eshop.eshop.models.Item;
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
    public ResponseEntity<List<Item>> getAllItems(){

    }

    @GetMapping(value = "${id}")
    public ResponseEntity<Item> getItemById(@PathVariable long id){

    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Item> createItem(@RequestBody Item item){

    }

    @PutMapping(value = "${id}/update")
    public ResponseEntity<Item> updateItem(@RequestBody Item item, @PathVariable long id){


    }

    @DeleteMapping(value = "${id}/delete")
    public ResponseEntity<String> deleteItem(@PathVariable long id){


    }
}
