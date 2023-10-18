package com.eshop.eshop.service.impl;

import com.eshop.eshop.dto.ItemDto;
import com.eshop.eshop.dto.ItemResponse;
import com.eshop.eshop.dto.converter.ItemConverter;
import com.eshop.eshop.exceptions.ItemNotFoundException;
import com.eshop.eshop.models.ItemCategoryEntity;
import com.eshop.eshop.models.ItemEntity;
import com.eshop.eshop.repository.ItemRepository;
import com.eshop.eshop.service.ItemService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Cacheable(value = "items")
    @Override
    public ItemResponse getAllItems(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<ItemEntity> items = itemRepository.findAll(pageable);
        List<ItemEntity> listOfItem = items.getContent();

        List<ItemDto> itemsDto = new ArrayList<>();

        for(ItemEntity item: listOfItem){
            itemsDto.add(ItemConverter.itemToItemDto(item));
        }

        ItemResponse itemResponse = new ItemResponse();
        itemResponse.setContent(itemsDto);
        itemResponse.setPageNumber(items.getNumber());
        itemResponse.setPageSize(items.getSize());
        itemResponse.setTotalElements(items.getTotalElements());
        itemResponse.setTotalPages(items.getTotalPages());
        itemResponse.setLast(items.isLast());

        return itemResponse;
    }

    @Override
    public ItemDto getItemById(long id) {
        ItemEntity item = itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Item could not be found"));
        return ItemConverter.itemToItemDto(item);
    }

    @Override
    public ItemDto createItem(ItemDto itemDto) {

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
        ItemEntity item = itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Item could not be deleted"));
        itemRepository.delete(item);
    }

    @Override
    public ItemDto setItemCategory(Long itemId, ItemCategoryEntity category) {

        ItemEntity item = itemRepository.findById(itemId).orElseThrow(() -> new ItemNotFoundException("Item not found"));

        List<ItemCategoryEntity> categories = item.getCategories();
        categories.add(category);
        item.setCategories(categories);

        return ItemConverter.itemToItemDto(itemRepository.save(item));
    }

    @Override
    public List<ItemDto> getItemsByCategory(String categoryName) {

        String filter = "%" + categoryName.toUpperCase() + "%";

        List<ItemEntity> items = itemRepository.selItemsByCategory(filter);
        return items.stream().map(ItemConverter::itemToItemDto).toList();
    }
}
