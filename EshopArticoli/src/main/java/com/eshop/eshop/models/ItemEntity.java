package com.eshop.eshop.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "ITEM")
public class ItemEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -5512011424604603219L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long itemId;

    private String name;
    private String details;
    private BigDecimal price;

    //contructor

    public ItemEntity(){}

    public ItemEntity(String name, String details, BigDecimal price) {
        this.name = name;
        this.details = details;
        this.price = price;
    }

    //getter & setter

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    //relations

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ivaId", referencedColumnName = "ivaId")
    private IvaEntity iva;

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<BarcodeEntity> barcodes = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "itemsCategories", joinColumns = @JoinColumn(name = "iId", referencedColumnName = "itemId"),
        inverseJoinColumns = @JoinColumn(name = "cId", referencedColumnName = "categoryId"))
    private List<ItemCategoryEntity> categories = new ArrayList<>();

    public IvaEntity getIva() {
        return iva;
    }

    public void setIva(IvaEntity iva) {
        this.iva = iva;
    }

    public List<BarcodeEntity> getBarcodes() {
        return barcodes;
    }

    public void setBarcodes(List<BarcodeEntity> barcodes) {
        this.barcodes = barcodes;
    }

    public List<ItemCategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<ItemCategoryEntity> categories) {
        this.categories = categories;
    }
}
