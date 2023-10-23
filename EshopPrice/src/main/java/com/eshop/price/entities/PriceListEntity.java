package com.eshop.price.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "PRICE-LIST")
public class PriceListEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long priceListId;

    private String description;

    private boolean obsolete;

    public PriceListEntity() {
    }

    public PriceListEntity(long priceListId, String description, boolean obsolete) {
        this.priceListId = priceListId;
        this.description = description;
        this.obsolete = obsolete;
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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "priceList")
    @JsonManagedReference
    private Set<PriceListDetailsEntity> priceListDetails = new HashSet<>();

    public Set<PriceListDetailsEntity> getPriceListDetails() {
        return priceListDetails;
    }

    public void setPriceListDetails(Set<PriceListDetailsEntity> priceListDetails) {
        this.priceListDetails = priceListDetails;
    }
}
