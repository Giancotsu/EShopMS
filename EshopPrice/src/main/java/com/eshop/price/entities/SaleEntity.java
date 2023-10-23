package com.eshop.price.entities;

import jakarta.persistence.*;

@Entity(name = "SALE")
public class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long saleId;

    private int amount;

    private String description;

    //constructor

    public SaleEntity() {
    }

    public SaleEntity(int amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    //getter & setter

    public long getSaleId() {
        return saleId;
    }

    public void setSaleId(long saleId) {
        this.saleId = saleId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //relations

    @ManyToOne
    @JoinColumn(name = "priceId", referencedColumnName = "priceId")
    private PriceEntity price;

    public PriceEntity getPrice() {
        return price;
    }

    public void setPrice(PriceEntity price) {
        this.price = price;
    }
}