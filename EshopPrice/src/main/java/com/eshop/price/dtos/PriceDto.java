package com.eshop.price.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class PriceDto {

    private long id;
    private BigDecimal price;
    private Set<SaleDto> sales;
}
