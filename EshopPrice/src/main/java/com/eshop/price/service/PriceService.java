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
    PriceDto setPriceSaleSingleByPriceId(long priceId, SaleDto saleDto);
    PriceDto setPriceSaleSingleByItemId(long itemId, SaleDto saleDto);
    String removeSaleSingleByItemId(long itemId, SaleDto saleDto);
    String removeSale(long saleId);
}
