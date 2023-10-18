package com.eshop.eshop.controllers;

import com.eshop.eshop.dto.ItemDto;
import com.eshop.eshop.dto.ItemResponse;
import com.eshop.eshop.exceptions.ErrorObj;
import com.eshop.eshop.models.ItemCategoryEntity;
import com.eshop.eshop.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/item")
@Tag(name = "item", description = "Item API")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @Operation(
            description = "Get all items endpoint",
            summary = "Get x items with pagination",
            parameters = {
                    @Parameter(
                            name = "pageNumber",
                            description = "Current page",
                            required = false
                    ),
                    @Parameter(
                            name = "pageSize",
                            description = "Number of elements for page",
                            required = false
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "All items saved in db",
                            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ItemResponse.class))}
                    )
            }
    )
    @GetMapping
    public ResponseEntity<ItemResponse> getAllItems(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ){

        ItemResponse itemResponse = itemService.getAllItems(pageNumber, pageSize);
        return new ResponseEntity<>(itemResponse, HttpStatus.OK);
    }

    @Operation(
            description = "Get all items by category",
            summary = "Get all items in x category",
            parameters = {
                    @Parameter(
                            name = "categoryName",
                            description = "Category filter",
                            required = true
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "All items in x category",
                            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ItemDto.class))}
                    )
            }
    )
    @GetMapping(value = "/category/{categoryName}")
    public ResponseEntity<List<ItemDto>> getItemsByCategory(@PathVariable("categoryName") String categoryName){

        return new ResponseEntity<>(itemService.getItemsByCategory(categoryName), HttpStatus.OK);
    }

    @Operation(
            description = "Get item by ID",
            summary = "Get item by ID",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "Item ID filter",
                            required = true
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Item with required ID",
                            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ItemDto.class))}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Item could not be found",
                            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObj.class))}
                    )
            }
    )
    @GetMapping(value = "/{id}")
    public ResponseEntity<ItemDto> getItemById(@PathVariable("id") long id){
        return new ResponseEntity<>(itemService.getItemById(id), HttpStatus.OK);
    }

    @Operation(
            description = "Create new item",
            summary = "Create new item",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Item to save in db",
                    required = true
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Item created",
                            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ItemDto.class))}
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Compilation Error"
                    )
            }
    )
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ItemDto> createItem(@Valid @RequestBody ItemDto itemDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new RuntimeException("Compilation Error");
        }
        return new ResponseEntity<>(itemService.createItem(itemDto), HttpStatus.CREATED);
    }

    @Operation(
            description = "Modify existing item",
            summary = "Modify existing item",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "Item ID filter",
                            required = true
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Item to modify in db",
                    required = true
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Item updated",
                            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ItemDto.class))}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Item could not be updated",
                            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObj.class))}
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Compilation Error"
                    )
            }
    )
    @PutMapping(value = "/{id}/update")
    public ResponseEntity<ItemDto> updateItem(@Valid @RequestBody ItemDto itemDto, @PathVariable("id") long id, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new RuntimeException("Compilation Error");
        }
        return new ResponseEntity<>(itemService.updateItem(itemDto, id), HttpStatus.OK);
    }

    @Operation(
            description = "Delete item",
            summary = "Delete item by ID",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "Item ID filter",
                            required = true
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Item deleted",
                            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Item could not be deleted",
                            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObj.class))}
                    )
            }
    )
    @DeleteMapping(value = "/{id}/delete")
    public ResponseEntity<String> deleteItem(@PathVariable("id") long id){

        itemService.deleteItemById(id);
        return new ResponseEntity<>("Item deleted", HttpStatus.OK);
    }

    @Operation(
            description = "Set item category",
            summary = "Set item category",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "Item ID filter",
                            required = true
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Category to give to the item",
                    required = true
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "category changed",
                            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ItemDto.class))}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Item not found",
                            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorObj.class))}
                    )
            }
    )
    @PostMapping(value = "/{id}/category")
    public ResponseEntity<ItemDto> setItemCategory(@PathVariable("id") Long itemId, @RequestBody ItemCategoryEntity category){

        return new ResponseEntity<>(itemService.setItemCategory(itemId, category), HttpStatus.OK);
    }
}
