package com.eshop.eshop.components;

import com.eshop.eshop.repository.ItemRepository;
import lombok.Data;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "")
@Data
public class ActuatorCustomEndpoint {

    private final ItemRepository itemRepository;

    public ActuatorCustomEndpoint(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
}
