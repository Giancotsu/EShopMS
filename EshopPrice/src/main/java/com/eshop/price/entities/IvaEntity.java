package com.eshop.price.entities;

import jakarta.persistence.Entity;

@Entity(name = "IVA")
public class IvaEntity {

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
}
