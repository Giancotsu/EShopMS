package com.eshop.items.components;

import com.eshop.items.entities.ItemEntity;
import com.eshop.items.repository.ItemRepository;
import lombok.Data;
import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Endpoint(id = "itemsInfo")
@Data
public class ActuatorCustomEndpoint {

    private final ItemRepository itemRepository;

    public ActuatorCustomEndpoint(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @ReadOperation
    public String itemsInfo(){
        return String.format("Quantity of items: %d", itemRepository.itemsCount());
    }

    @ReadOperation
    public List<ItemEntity> itemsInfo(@Selector String name){
        if(name.equalsIgnoreCase("all")){
            return itemRepository.findAll();
        }else{
            List<ItemEntity> items = new ArrayList<>();
            try{
                long id = Long.parseLong(name);
                Optional<ItemEntity> optionalItem= itemRepository.findById(id);
                optionalItem.ifPresent(items::add);
            }catch (NumberFormatException exception){
                System.out.println(Arrays.toString(exception.getStackTrace()));
            }
            return items;
        }
    }

    @WriteOperation
    public String postEndpointExample(@Selector String name){
        return "Post Enpoint: " + name;
    }

    @DeleteOperation
    public String deleteEndpointExample(@Selector String name){
        return "Delete Enpoint: " + name;
    }
}
