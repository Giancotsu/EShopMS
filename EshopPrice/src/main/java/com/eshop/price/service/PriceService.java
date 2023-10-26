package com.eshop.price.service;

import com.eshop.price.dtos.PriceDto;

import java.math.BigDecimal;

public interface PriceService {

    BigDecimal getPriceByItem(long itemId);
    PriceDto setPriceToItem(PriceDto price, long itemId);
}
