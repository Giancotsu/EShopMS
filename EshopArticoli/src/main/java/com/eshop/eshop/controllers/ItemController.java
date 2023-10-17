package com.eshop.eshop.controllers;

import com.eshop.eshop.dto.ItemDto;
import com.eshop.eshop.dto.ItemResponse;
import com.eshop.eshop.models.ItemCategoryEntity;
import com.eshop.eshop.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<ItemResponse> getAllItems(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ){

        ItemResponse itemResponse = itemService.getAllItems(pageNumber, pageSize);
        return new ResponseEntity<>(itemResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/category/{categoryName}")
    public ResponseEntity<List<ItemDto>> getItemsByCategory(@PathVariable("categoryName") String categoryName){

        return new ResponseEntity<>(itemService.getItemsByCategory(categoryName), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ItemDto> getItemById(@PathVariable("id") long id){
        return new ResponseEntity<>(itemService.getItemById(id), HttpStatus.OK);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ItemDto> createItem(@Valid @RequestBody ItemDto itemDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new RuntimeException("Errore di compilazione");
        }
        return new ResponseEntity<>(itemService.createItem(itemDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}/update")
    public ResponseEntity<ItemDto> updateItem(@RequestBody ItemDto itemDto, @PathVariable("id") long id){
        return new ResponseEntity<>(itemService.updateItem(itemDto, id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/delete")
    public ResponseEntity<String> deleteItem(@PathVariable("id") long id){

        itemService.deleteItemById(id);
        return new ResponseEntity<>("Item deleted", HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/category")
    public ResponseEntity<ItemDto> setItemCategory(@PathVariable("id") Long itemId, @RequestBody ItemCategoryEntity category){

        return new ResponseEntity<>(itemService.setItemCategory(itemId, category), HttpStatus.OK);
    }
}
