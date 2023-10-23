package com.eshop.price.service;

import com.eshop.price.dtos.PriceDto;

public interface PriceService {

    PriceDto getPriceByItem(long itemId);
    PriceDto setPriceToItem(PriceDto price, long itemId);
}
