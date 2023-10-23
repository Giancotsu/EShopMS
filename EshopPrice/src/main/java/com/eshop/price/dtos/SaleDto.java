package com.eshop.price.dtos;

import lombok.Data;

@Data
public class SaleDto {

    private long id;
    private int amount;
    private String description;
    private PriceDto price;
}
