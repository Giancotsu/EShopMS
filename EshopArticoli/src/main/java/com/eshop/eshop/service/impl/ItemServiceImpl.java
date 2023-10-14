package com.eshop.eshop.service.impl;

import com.eshop.eshop.dto.ItemDto;
import com.eshop.eshop.dto.converter.ItemConverter;
import com.eshop.eshop.models.ItemEntity;
import com.eshop.eshop.repository.ItemRepository;
import com.eshop.eshop.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<ItemDto> getAllItems() {

        List<ItemDto> itemsDto = new ArrayList<>();
        List<ItemEntity> items = itemRepository.findAll();

        for(ItemEntity item: items){
            itemsDto.add(ItemConverter.itemToItemDto(item));
        }

        return itemsDto;
    }

    @Override
    public ItemDto createItem(ItemDto itemDto) {
        //ItemEntity item = new ItemEntity(itemDto.getName(), itemDto.getDetails(), itemDto.getPrice());
        ItemEntity item = ItemConverter.itemDtoToitem(itemDto);

        ItemEntity newItem = itemRepository.save(item);

        return ItemConverter.itemToItemDto(newItem);
    }
}
