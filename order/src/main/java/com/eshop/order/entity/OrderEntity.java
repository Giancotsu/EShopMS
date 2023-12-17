package com.eshop.order.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "t_ORDER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;
    private String orderNumber;

    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL)
    private List<OrderItems> itemList;


}
