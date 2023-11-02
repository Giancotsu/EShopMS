package com.eshop.price.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.ToString;

import java.util.Set;

@Entity(name = "IVA")
public class IvaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ivaId;
    private int value;

    public IvaEntity() {
    }

    public IvaEntity(int value) {
        this.value = value;
    }

    public long getIvaId() {
        return ivaId;
    }

    public void setIvaId(long ivaId) {
        this.ivaId = ivaId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @OneToMany(mappedBy = "iva")
    private Set<PriceEntity> prices;

    public Set<PriceEntity> getPrices() {
        return prices;
    }

    public void setPrices(Set<PriceEntity> prices) {
        this.prices = prices;
    }
}
