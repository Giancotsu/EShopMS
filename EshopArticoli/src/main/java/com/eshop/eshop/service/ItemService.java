package com.eshop.eshop.service;

import com.eshop.eshop.dto.ItemDto;
import com.eshop.eshop.models.ItemEntity;

import java.util.List;

public interface ItemService {

    List<ItemEntity> getAllItems();
    ItemDto createItem(ItemDto itemDto);
}
