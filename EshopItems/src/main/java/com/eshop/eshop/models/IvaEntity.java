package com.eshop.eshop.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "IVA")
public class IvaEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 8133237913381337558L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ivaId;
    private int ivaValue;

    //contructor

    public IvaEntity() {}

    public IvaEntity(int ivaValue) {
        this.ivaValue = ivaValue;
    }

    //getter & setter

    public long getIvaId() {
        return ivaId;
    }

    public void setIvaId(long ivaId) {
        this.ivaId = ivaId;
    }

    public int getIvaValue() {
        return ivaValue;
    }

    public void setIvaValue(int ivaValue) {
        this.ivaValue = ivaValue;
    }

    //relations
    @OneToMany(mappedBy = "iva")
    private List<ItemEntity> items = new ArrayList<>();
}
