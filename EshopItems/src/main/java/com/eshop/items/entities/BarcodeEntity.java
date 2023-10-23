package com.eshop.items.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity(name = "BARCODE")
public class BarcodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long barcodeId;

    private String barcode;

    //contructor

    public BarcodeEntity() {
    }

    public BarcodeEntity(String barcode) {
        this.barcode = barcode;
    }

    //getter & setter

    public long getBarcodeId() {
        return barcodeId;
    }

    public void setBarcodeId(long barcodeId) {
        this.barcodeId = barcodeId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    //relations
    @ManyToOne()
    @JoinColumn(name = "itemId", referencedColumnName = "itemId")
    @JsonBackReference
    private ItemEntity item;
}
