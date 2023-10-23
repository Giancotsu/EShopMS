package com.eshop.price.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "PRICE-LIST")
public class PriceListEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long priceListId;

    private String description;

    private boolean obsolete;

    public PriceListEntity() {
    }

    public PriceListEntity(String description, boolean obsolete) {
        this.description = description;
        this.obsolete = obsolete;
    }

    public long getPriceListId() {
        return priceListId;
    }

    public void setPriceListId(long priceListId) {
        this.priceListId = priceListId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isObsolete() {
        return obsolete;
    }

    public void setObsolete(boolean obsolete) {
        this.obsolete = obsolete;
    }


}
