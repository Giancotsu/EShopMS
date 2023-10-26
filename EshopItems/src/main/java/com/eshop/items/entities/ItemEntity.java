package com.eshop.items.entities;

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

    //contructor

    public ItemEntity(){}

    public ItemEntity(String name, String details) {
        this.name = name;
        this.details = details;
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

    //relations

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "itemsCategories", joinColumns = @JoinColumn(name = "iId", referencedColumnName = "itemId"),
        inverseJoinColumns = @JoinColumn(name = "cId", referencedColumnName = "categoryId"))
    private Set<ItemCategoryEntity> categories = new HashSet<>();

    public Set<ItemCategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(Set<ItemCategoryEntity> categories) {
        this.categories = categories;
    }
}
