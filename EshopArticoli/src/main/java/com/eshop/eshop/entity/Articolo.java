package com.eshop.eshop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;

@Entity
@Builder
public class Articolo {

    @Id
    private int articoloId;
    private String name;
    private String details;
    private double price;

    public Articolo(){}

    public Articolo(int articoloId, String name, String details, double price) {
        this.articoloId = articoloId;
        this.name = name;
        this.details = details;
        this.price = price;
    }
}
