package com.eshop.eshop.components;

import com.eshop.eshop.repository.ItemRepository;
import lombok.Data;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

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
}
