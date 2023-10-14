package com.eshop.eshop.service;

import com.eshop.eshop.dto.ItemDto;
import com.eshop.eshop.dto.ItemResponse;

public interface ItemService {

    ItemResponse getAllItems(int pageNumber, int pageSize);
    ItemDto getItemById(long id);
    ItemDto createItem(ItemDto itemDto);
    ItemDto updateItem(ItemDto itemDto, long id);
    void deleteItemById(long id);
}
