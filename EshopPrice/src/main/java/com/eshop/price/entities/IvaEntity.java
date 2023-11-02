package com.eshop.price.entities;

import jakarta.persistence.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "priceId")
    private PriceEntity price;

}
