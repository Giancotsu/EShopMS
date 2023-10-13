package com.eshop.eshop.dto;

import lombok.Data;

@Data
public class ItemDto {

    private long id;
    private String name;
    private String details;
    private double price;
}
