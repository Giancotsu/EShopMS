package com.eshop.eshop.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemDto {

    private long id;
    private String name;
    private String details;
    private BigDecimal price;
}
