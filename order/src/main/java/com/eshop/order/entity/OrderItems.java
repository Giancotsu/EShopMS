package com.eshop.order.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name = "ORDER-ITEMS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderItemsId;
    private String itemName;
    private BigDecimal price;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "orderId", nullable = false)
    private OrderEntity orderEntity;
}
