package com.eshop.eshop.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serial;
import java.io.Serializable;

@Entity(name = "CATEGORY")
public class ItemCategoryEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 8572862759675107427L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryId;

    private String categoryName;

    //contructor

    public ItemCategoryEntity() {
    }

    public ItemCategoryEntity(String categoryName) {
        this.categoryName = categoryName;
    }

    //Getter & Setter

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
