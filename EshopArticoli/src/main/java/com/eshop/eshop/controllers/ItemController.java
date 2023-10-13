package com.eshop.eshop.controllers;

import com.eshop.eshop.dto.ItemDto;
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
        List<ItemEntity> items = itemService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping(value = "${id}")
    public ResponseEntity<ItemEntity> getItemById(@PathVariable long id){

    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ItemDto> createItem(@RequestBody ItemDto itemDto){
        return new ResponseEntity<>(itemService.createItem(itemDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "${id}/update")
    public ResponseEntity<ItemEntity> updateItem(@RequestBody ItemEntity item, @PathVariable long id){


    }

    @DeleteMapping(value = "${id}/delete")
    public ResponseEntity<String> deleteItem(@PathVariable long id){


    }
}
