package com.eshop.items.service;

import com.eshop.items.dto.ItemDto;
import com.eshop.items.dto.ItemResponse;
import com.eshop.items.entities.ItemCategoryEntity;

import java.util.List;

public interface ItemService {

    ItemResponse getAllItems(int pageNumber, int pageSize);
    ItemDto getItemById(long id);
    ItemDto createItem(ItemDto itemDto);
    ItemDto updateItem(ItemDto itemDto, long id);
    void deleteItemById(long id);
    ItemDto setItemCategory(Long itemId, ItemCategoryEntity category);
    ItemDto removeItemCategory(Long itemId, ItemCategoryEntity category);
    List<ItemDto> getItemsByCategory(String categoryName);
}
