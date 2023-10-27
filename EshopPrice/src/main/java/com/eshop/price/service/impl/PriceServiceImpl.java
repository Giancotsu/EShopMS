package com.eshop.price.service.impl;

import com.eshop.price.dtos.PriceDto;
import com.eshop.price.dtos.mapper.PriceMapper;
import com.eshop.price.entities.PriceEntity;
import com.eshop.price.openfeign.ItemClient;
import com.eshop.price.repositories.PriceRepository;
import com.eshop.price.service.PriceService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;
    private final ItemClient itemClient;

    public PriceServiceImpl(PriceRepository priceRepository, ItemClient itemClient) {
        this.priceRepository = priceRepository;
        this.itemClient = itemClient;
    }

    @Override
    public BigDecimal getPriceByItem(long itemId) {
        PriceEntity priceEntity = priceRepository.findPriceEntityByItemId(itemId);
        if(priceEntity==null){
            return BigDecimal.valueOf(0);
        }
        return priceEntity.getPrice();
    }

    @Override
    public PriceDto setPriceToItem(long itemId, BigDecimal price) {
        PriceDto priceDto;
        PriceEntity priceEntity = priceRepository.findPriceEntityByItemId(itemId);
        if(priceEntity!=null){
            priceDto = PriceMapper.entityToDto(priceEntity);
            priceDto.setPrice(price);
        }else{
            priceDto = new PriceDto();
            priceDto.setItemId(itemId);
            priceDto.setPrice(price);
            priceDto.setSales(new HashSet<>());
        }

        System.out.println(itemClient.clearItemsCache(priceDto.getItemId()));
        return PriceMapper.entityToDto(priceRepository.save(PriceMapper.dtoToEntity(priceDto)));
    }
}
