package com.eshop.items.service.impl;

import com.eshop.items.dto.ItemDto;
import com.eshop.items.dto.ItemResponse;
import com.eshop.items.dto.converter.ItemConverter;
import com.eshop.items.exceptions.ItemNotFoundException;
import com.eshop.items.entities.ItemCategoryEntity;
import com.eshop.items.entities.ItemEntity;
import com.eshop.items.entities.IvaEntity;
import com.eshop.items.repository.ItemRepository;
import com.eshop.items.service.ItemService;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@CacheConfig(cacheNames={"items"})
public class ItemServiceImpl implements ItemService {

    private final CacheManager cacheManager;
    private final ItemRepository itemRepository;

    public ItemServiceImpl(CacheManager cacheManager, ItemRepository itemRepository) {
        this.cacheManager = cacheManager;
        this.itemRepository = itemRepository;
    }

    @Override
    @Cacheable
    public ItemResponse getAllItems(int pageNumber, int pageSize) {

        System.err.println("ALL ITEMS:");

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
    @Cacheable(value = "item", key = "#id", sync = true)
    public ItemDto getItemById(long id) {

        System.err.println("ITEM BY ID:");

        ItemEntity item = itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Item could not be found"));

        return ItemConverter.itemToItemDto(item);
    }

    @Override
    @Cacheable(value = "category")
    public List<ItemDto> getItemsByCategory(String categoryName) {

        System.err.println("ITEM BY CATEGORY:");

        String filter = "%" + categoryName.toUpperCase() + "%";

        List<ItemEntity> items = itemRepository.selItemsByCategory(filter);

        return items.stream().map(ItemConverter::itemToItemDto).toList();
    }

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = "items", allEntries = true),
            @CacheEvict(cacheNames = "category", allEntries = true),
    })
    public ItemDto createItem(ItemDto itemDto) {

        System.err.println("ITEM CREATED:");

        ItemEntity item = ItemConverter.itemDtoToitem(itemDto);

        ItemEntity newItem = itemRepository.save(item);

        return ItemConverter.itemToItemDto(newItem);
    }

    @Override
    @Caching(put = {
            @CachePut(cacheNames = "item", key = "#id"),
    },
    evict = {
            @CacheEvict(cacheNames = "items", allEntries = true),
            @CacheEvict(cacheNames = "category", allEntries = true),
    })
    public ItemDto updateItem(ItemDto itemDto, long id) {

        System.err.println("ITEM UPDATED:");

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
        System.err.println("ITEM DELETED:");
        ItemEntity item = itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Item could not be deleted"));
        itemRepository.delete(item);
        this.evictCache(item.getItemId());
    }

    @Override
    public ItemDto setItemCategory(Long itemId, ItemCategoryEntity category) {

        System.err.println("SET ITEM CATEGORY:");

        ItemEntity item = itemRepository.findById(itemId).orElseThrow(() -> new ItemNotFoundException("Item not found"));

        Set<ItemCategoryEntity> categories = item.getCategories();
        categories.add(category);
        item.setCategories(categories);

        this.evictCache(itemId);

        return ItemConverter.itemToItemDto(itemRepository.save(item));
    }

    @Override
    public ItemDto removeItemCategory(Long itemId, ItemCategoryEntity category) {

        System.err.println("REMOVE ITEM CATEGORY:");

        ItemEntity item = itemRepository.findById(itemId).orElseThrow(() -> new ItemNotFoundException("Item not found"));

        Set<ItemCategoryEntity> categories = item.getCategories();
        System.err.println(categories.remove(category));
        item.setCategories(categories);

        this.evictCache(itemId);

        return ItemConverter.itemToItemDto(itemRepository.save(item));
    }

    @Override
    public ItemDto setItemIva(Long itemId, IvaEntity iva) {

        System.err.println("SET ITEM IVA:");

        ItemEntity item = itemRepository.findById(itemId).orElseThrow(() -> new ItemNotFoundException("Item not found"));

        item.setIva(iva);

        this.evictCache(itemId);

        return ItemConverter.itemToItemDto(itemRepository.save(item));
    }


    public void evictCache(Long id){
        cacheManager.getCache("item").evict(id);
        cacheManager.getCache("items").clear();
        cacheManager.getCache("category").clear();
        System.out.println("cache pulita");
    }

}
