package com.eshop.eshop.dto.converter;

import com.eshop.eshop.dto.ItemDto;
import com.eshop.eshop.models.ItemEntity;

public class ItemConverter {

    public static ItemEntity itemDtoToitem(ItemDto itemDto) {
        ItemEntity item = new ItemEntity();
        item.setItemId(itemDto.getId());
        item.setName(itemDto.getName());
        item.setDetails(itemDto.getDetails());
        item.setPrice(itemDto.getPrice());

        return item;
    }

    public static ItemDto itemToItemDto(ItemEntity item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getItemId());
        itemDto.setName(item.getName());
        itemDto.setDetails(item.getDetails());
        itemDto.setPrice(item.getPrice());

        return itemDto;
    }
}
