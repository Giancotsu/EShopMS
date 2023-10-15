package com.eshop.eshop.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "ITEM")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long itemId;
    private String name;
    private String details;
    private BigDecimal price;

    //contructor

    public ItemEntity(){}

    public ItemEntity(String name, String details, BigDecimal price) {
        this.name = name;
        this.details = details;
        this.price = price;
    }

    //getter & setter

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    //relations

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ivaId")
    private IvaEntity iva;


}
