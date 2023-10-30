package com.eshop.price.dtos;

import lombok.Data;

@Data
public class SaleDto {

    private long id;
    private String name;
    private String description;
    private int amount;
}
