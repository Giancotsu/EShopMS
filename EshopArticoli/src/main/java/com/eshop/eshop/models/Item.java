package com.eshop.eshop.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;

@Entity
@Builder
public class Item {

    @Id
    private long itemId;
    private String name;
    private String details;
    private double price;

    public Item(){}

    public Item(String name, String details, double price) {
        this.name = name;
        this.details = details;
        this.price = price;
    }
}
