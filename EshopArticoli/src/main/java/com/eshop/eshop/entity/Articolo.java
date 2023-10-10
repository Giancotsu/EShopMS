package com.eshop.eshop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Articolo {

    @Id
    private int articoloId;
    private String name;
    private String description;
    private double price;

}
