package com.eshop.eshop.service.impl;

import com.eshop.eshop.dto.ItemDto;
import com.eshop.eshop.dto.converter.ItemConverter;
import com.eshop.eshop.models.ItemEntity;
import com.eshop.eshop.repository.ItemRepository;
import com.eshop.eshop.service.ItemService;

public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public ItemDto createItem(ItemDto itemDto) {
        //ItemEntity item = new ItemEntity(itemDto.getName(), itemDto.getDetails(), itemDto.getPrice());
        ItemEntity item = ItemConverter.itemDtoToitem(itemDto);

        ItemEntity newItem = itemRepository.save(item);

        return ItemConverter.itemToItemDto(newItem);
    }
}
