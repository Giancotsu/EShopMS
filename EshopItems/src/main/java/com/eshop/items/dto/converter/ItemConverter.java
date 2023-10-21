package com.eshop.items.dto.converter;

import com.eshop.items.dto.ItemDto;
import com.eshop.items.models.ItemEntity;

public class ItemConverter {

    public static ItemEntity itemDtoToitem(ItemDto itemDto) {
        ItemEntity item = new ItemEntity();
        item.setItemId(itemDto.getId());
        item.setName(itemDto.getName());
        item.setDetails(itemDto.getDetails());
        item.setPrice(itemDto.getPrice());
        item.setCategories(itemDto.getCategories());
        item.setIva(itemDto.getIva());

        return item;
    }

    public static ItemDto itemToItemDto(ItemEntity item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getItemId());
        itemDto.setName(item.getName());
        itemDto.setDetails(item.getDetails());
        itemDto.setPrice(item.getPrice());
        itemDto.setCategories(item.getCategories());
        itemDto.setIva(item.getIva());

        return itemDto;
    }
}
