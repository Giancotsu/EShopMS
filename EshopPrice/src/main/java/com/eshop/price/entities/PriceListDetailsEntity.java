package com.eshop.price.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "PRICE-LIST-DETAILS")
public class PriceListDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long priceListDetailsId;

    private long itemId;

    private BigDecimal price;

    public PriceListDetailsEntity() {
    }

    public PriceListDetailsEntity(long itemId, BigDecimal price) {
        this.itemId = itemId;
        this.price = price;
    }

    public PriceListDetailsEntity(long itemId, BigDecimal price, PriceListEntity priceList) {
        this.itemId = itemId;
        this.price = price;
        this.priceList = priceList;
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

    @ManyToOne
    @JoinColumn(name = "priceListId", referencedColumnName = "priceListId")
    @JsonBackReference
    private PriceListEntity priceList;

    public PriceListEntity getPriceList() {
        return priceList;
    }

    public void setPriceList(PriceListEntity priceList) {
        this.priceList = priceList;
    }
}
