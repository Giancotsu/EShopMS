package com.eshop.eshop.components;

import com.eshop.eshop.repository.ItemRepository;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ItemsInfoContributor implements InfoContributor {

    private final ItemRepository itemRepository;

    public ItemsInfoContributor(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void contribute(Info.Builder builder) {

        long itemsQty = itemRepository.itemsCount();

        Map<String, Long> itemMap = new HashMap<>();
        itemMap.put("Quantity of items", itemsQty);

        builder.withDetail("items-info", itemMap);
    }
}
