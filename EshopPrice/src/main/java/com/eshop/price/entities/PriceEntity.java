package com.eshop.price.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "PRICE")
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long priceId;

    private BigDecimal price;

    //constructor

    public PriceEntity() {
    }

    public PriceEntity(BigDecimal price) {
        this.price = price;
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

    //relations

    @OneToMany(mappedBy = "price")
    private Set<SaleEntity> sales = new HashSet<>();

    public Set<SaleEntity> getSales() {
        return sales;
    }

    public void setSales(Set<SaleEntity> sales) {
        this.sales = sales;
    }
}
