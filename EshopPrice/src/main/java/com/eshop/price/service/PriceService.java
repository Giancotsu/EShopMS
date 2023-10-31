package com.eshop.price.service;

import com.eshop.price.dtos.ItemClientRequestPriceCategory;
import com.eshop.price.dtos.PriceDto;
import com.eshop.price.dtos.SaleDto;

import java.math.BigDecimal;

public interface PriceService {

    PriceDto getPriceById(long priceId);
    BigDecimal getPriceByItem(long itemId);
    PriceDto setPriceToItem(long itemId, BigDecimal price);
    PriceDto setItemPriceAndCategories(ItemClientRequestPriceCategory itemClientRequest);
    PriceDto setPriceSaleSingle(long priceId, SaleDto saleDto);
}
