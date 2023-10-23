package com.eshop.price.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity(name = "PRICE-LIST-DETAILS")
public class PriceListDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long priceListDetailsId;

    private long itemId;

    private BigDecimal price;

    public PriceListDetails() {
    }

    public PriceListDetails(long itemId, BigDecimal price) {
        this.itemId = itemId;
        this.price = price;
    }

    public long getPriceListDetailsId() {
        return priceListDetailsId;
    }

    public void setPriceListDetailsId(long priceListDetailsId) {
        this.priceListDetailsId = priceListDetailsId;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
