package com.eshop.eshop.models;

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
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "itemId", referencedColumnName = "itemId")
    private ItemEntity item;
}
