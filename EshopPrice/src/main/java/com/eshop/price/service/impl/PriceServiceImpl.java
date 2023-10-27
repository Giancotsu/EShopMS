package com.eshop.price.service.impl;

import com.eshop.price.dtos.PriceDto;
import com.eshop.price.entities.PriceEntity;
import com.eshop.price.repositories.PriceRepository;
import com.eshop.price.service.PriceService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
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
    public PriceDto setPriceToItem(PriceDto price, long itemId) {
        return null;
    }
}
