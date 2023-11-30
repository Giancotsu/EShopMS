package com.eshop.items.service.impl;

import com.eshop.items.dto.ItemDto;
import com.eshop.items.dto.ItemResponse;
import com.eshop.items.dto.SetPriceCategoriesDto;
import com.eshop.items.dto.converter.ItemConverter;
import com.eshop.items.exceptions.ItemNotFoundException;
import com.eshop.items.entities.ItemCategoryEntity;
import com.eshop.items.entities.ItemEntity;
import com.eshop.items.openfeign.PriceClient;
import com.eshop.items.repository.ItemRepository;
import com.eshop.items.service.ItemService;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.*;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@CacheConfig(cacheNames={"items"})
public class ItemServiceImpl implements ItemService {

    private final CacheManager cacheManager;
    private final ItemRepository itemRepository;
    private final PriceClient priceClient;
    private final CircuitBreaker circuitBreaker;

    public ItemServiceImpl(CacheManager cacheManager, ItemRepository itemRepository, PriceClient priceClient, CircuitBreakerFactory<?, ?> circuitBreakerFactory) {
        this.cacheManager = cacheManager;
        this.itemRepository = itemRepository;
        this.priceClient = priceClient;
        this.circuitBreaker = circuitBreakerFactory.create("myCircuitBreaker");
    }

    private BigDecimal getItemPrice(Long id){
        return circuitBreaker.run(() -> priceClient.getItemPrice(id),
                throwable -> getItemPriceFallback());
    }

    private BigDecimal getItemPriceFallback(){
        System.out.println("get item price fallback method");
        return BigDecimal.valueOf(999999999);
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
            ItemDto itemDto = ItemConverter.itemToItemDto(item);
            itemDto.setPrice(this.getItemPrice(itemDto.getId()));
            itemsDto.add(itemDto);
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
        ItemDto itemDto = ItemConverter.itemToItemDto(item);
        itemDto.setPrice(this.getItemPrice(itemDto.getId()));

        return itemDto;
    }

    @Override
    @Cacheable(value = "category")
    public List<ItemDto> getItemsByCategory(String categoryName) {

        System.err.println("ITEM BY CATEGORY:");

        String filter = "%" + categoryName.toUpperCase() + "%";

        List<ItemEntity> items = itemRepository.selItemsByCategory(filter);
        List<ItemDto> itemDtos = new ArrayList<>();
        items.forEach(item -> {
            ItemDto itemDto = ItemConverter.itemToItemDto(item);
            itemDto.setPrice(this.getItemPrice(item.getItemId()));
            itemDtos.add(itemDto);
        });

        return itemDtos;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = "items", allEntries = true),
            @CacheEvict(cacheNames = "category", allEntries = true),
    })
    public ItemDto createItem(ItemDto itemDto) {

        priceClient.isOn();

        ItemEntity newItem = itemRepository.save(ItemConverter.itemDtoToitem(itemDto));

        BigDecimal price = itemDto.getPrice();

        Set<Long> itemCategoriesIds = new HashSet<>();
        itemDto.getCategories().forEach(category -> itemCategoriesIds.add(category.getCategoryId()));

        SetPriceCategoriesDto priceRequest = new SetPriceCategoriesDto();
        priceRequest.setItemId(newItem.getItemId());
        priceRequest.setPrice(price);
        priceRequest.setItemCategoriesId(itemCategoriesIds);

        priceClient.setItemPriceAndCategories(priceRequest);

        ItemDto response = ItemConverter.itemToItemDto(newItem);
        response.setPrice(price);
        System.err.println("ITEM CREATED:");
        return response;
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

        priceClient.isOn();

        itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Item could not be updated"));

        if(itemDto.getId() != id){
            throw new ItemNotFoundException("Item could not be updated");
        }

        ItemEntity newItem = itemRepository.save(ItemConverter.itemDtoToitem(itemDto));

        BigDecimal price = itemDto.getPrice();

        Set<Long> itemCategoriesIds = new HashSet<>();
        itemDto.getCategories().forEach(category -> itemCategoriesIds.add(category.getCategoryId()));

        SetPriceCategoriesDto priceRequest = new SetPriceCategoriesDto();
        priceRequest.setItemId(newItem.getItemId());
        priceRequest.setPrice(price);
        priceRequest.setItemCategoriesId(itemCategoriesIds);

        priceClient.setItemPriceAndCategories(priceRequest);

        ItemDto response = ItemConverter.itemToItemDto(newItem);
        response.setPrice(price);
        System.err.println("ITEM UPDATED:");
        return response;
    }

    @Override
    public void deleteItemById(long id) {
        System.err.println("ITEM DELETED:");
        ItemEntity item = itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Item could not be deleted"));
        itemRepository.delete(item);
        this.evictCache(item.getItemId());
    }

    @Override
    @Deprecated
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
    @Deprecated
    public ItemDto removeItemCategory(Long itemId, ItemCategoryEntity category) {

        System.err.println("REMOVE ITEM CATEGORY:");

        ItemEntity item = itemRepository.findById(itemId).orElseThrow(() -> new ItemNotFoundException("Item not found"));

        Set<ItemCategoryEntity> categories = item.getCategories();
        categories.remove(category);
        item.setCategories(categories);

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
