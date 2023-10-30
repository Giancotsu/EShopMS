package com.eshop.price.service;

import com.eshop.price.dtos.ItemClientRequestPriceCategory;
import com.eshop.price.dtos.PriceDto;

import java.math.BigDecimal;

public interface PriceService {

    BigDecimal getPriceByItem(long itemId);
    PriceDto setPriceToItem(long itemId, BigDecimal price);
    PriceDto setItemPriceAndCategories(ItemClientRequestPriceCategory itemClientRequest);
}
