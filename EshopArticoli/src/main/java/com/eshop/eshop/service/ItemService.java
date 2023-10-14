package com.eshop.eshop.service;

import com.eshop.eshop.dto.ItemDto;

import java.util.List;

public interface ItemService {

    List<ItemDto> getAllItems();
    ItemDto createItem(ItemDto itemDto);
}
