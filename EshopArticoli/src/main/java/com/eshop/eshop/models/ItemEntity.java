package com.eshop.eshop.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;

@Entity(name = "ITEM")
@Builder
public class ItemEntity {

    @Id
    private long itemId;
    private String name;
    private String details;
    private double price;

    public ItemEntity(){}

    public ItemEntity(String name, String details, double price) {
        this.name = name;
        this.details = details;
        this.price = price;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
