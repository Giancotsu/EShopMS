package com.eshop.price.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity(name = "SALE")
public class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long saleId;
    private String name;
    private String description;
    private int amount;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SaleEntity that = (SaleEntity) o;

        return getSaleId() == that.getSaleId();
    }

    @Override
    public int hashCode() {
        return (int) (getSaleId() ^ (getSaleId() >>> 32));
    }
}