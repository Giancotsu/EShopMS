package com.eshop.price.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "PRICE")
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long priceId;
    private long itemId;
    private BigDecimal price;
    private Set<Long> itemCategoriesId;

    //constructor

    public PriceEntity() {
    }

    public PriceEntity(BigDecimal price, long itemId) {
        this.price = price;
        this.itemId = itemId;
    }

    //getter & setter

    public long getPriceId() {
        return priceId;
    }

    public void setPriceId(long priceId) {
        this.priceId = priceId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public Set<Long> getItemCategoriesId() {
        return itemCategoriesId;
    }

    public void setItemCategoriesId(Set<Long> itemCategoriesId) {
        this.itemCategoriesId = itemCategoriesId;
    }

    //relations

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "priceSale", joinColumns = @JoinColumn(name = "pId", referencedColumnName = "priceId"),
            inverseJoinColumns = @JoinColumn(name = "sId", referencedColumnName = "saleId"))
    private Set<SaleEntity> sales = new HashSet<>();

    public Set<SaleEntity> getSales() {
        return sales;
    }

    public void setSales(Set<SaleEntity> sales) {
        this.sales = sales;
    }

    @OneToMany(mappedBy = "price", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<IvaEntity> iva;
}
