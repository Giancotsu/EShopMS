package com.eshop.eshop.service.impl;

import com.eshop.eshop.dto.ItemDto;
import com.eshop.eshop.dto.converter.ItemConverter;
import com.eshop.eshop.exceptions.ItemNotFoundException;
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
    public ItemDto getItemById(long id) {
        ItemEntity item = itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Item could not be found"));
        return ItemConverter.itemToItemDto(item);
    }

    @Override
    public ItemDto createItem(ItemDto itemDto) {
        //ItemEntity item = new ItemEntity(itemDto.getName(), itemDto.getDetails(), itemDto.getPrice());
        ItemEntity item = ItemConverter.itemDtoToitem(itemDto);

        ItemEntity newItem = itemRepository.save(item);

        return ItemConverter.itemToItemDto(newItem);
    }

    @Override
    public ItemDto updateItem(ItemDto itemDto, long id) {

        itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Item could not be updated"));

        if(itemDto.getId() != id){
            throw new ItemNotFoundException("Item could not be updated");
        }

        ItemEntity item = ItemConverter.itemDtoToitem(itemDto);

        ItemEntity updatedItem = itemRepository.save(item);

        return ItemConverter.itemToItemDto(updatedItem);
    }

    @Override
    public void deleteItemById(long id) {
        //ItemEntity item = itemRepository.findById()
    }
}
