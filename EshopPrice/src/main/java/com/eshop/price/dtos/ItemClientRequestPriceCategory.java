package com.eshop.price.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class ItemClientRequestPriceCategory {

    private Long itemId;
    private BigDecimal price;
    private Set<Long> itemCategoriesId;
}
