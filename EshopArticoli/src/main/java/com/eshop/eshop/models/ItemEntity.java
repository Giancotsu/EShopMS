package com.eshop.eshop.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "itemsCategories", joinColumns = @JoinColumn(name = "iId", referencedColumnName = "itemId"),
        inverseJoinColumns = @JoinColumn(name = "cId", referencedColumnName = "categoryId"))
    private Set<ItemCategoryEntity> categories = new HashSet<>();

    public IvaEntity getIva() {
        return iva;
    }

    public void setIva(IvaEntity iva) {
        this.iva = iva;
    }

    public Set<ItemCategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(Set<ItemCategoryEntity> categories) {
        this.categories = categories;
    }
}
