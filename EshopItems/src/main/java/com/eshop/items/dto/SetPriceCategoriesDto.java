package com.eshop.items.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;


@Data
public class SetPriceCategoriesDto {

    private Long itemId;
    private BigDecimal price;
    private Set<Long> itemCategoriesId;
}
