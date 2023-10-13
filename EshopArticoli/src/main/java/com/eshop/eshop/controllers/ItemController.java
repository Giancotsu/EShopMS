package com.eshop.eshop.controllers;

import com.eshop.eshop.models.Item;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/item/")
public class ItemController {



    @GetMapping
    public ResponseEntity<List<Item>> getAllItems(){

    }

    @GetMapping(value = "${id}")
    public ResponseEntity<Item> getItemById(@PathVariable long id){

    }
}
