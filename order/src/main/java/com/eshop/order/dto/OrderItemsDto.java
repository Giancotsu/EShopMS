package com.eshop.order.dto;

import com.eshop.order.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemsDto {

    private long orderItemsId;
    private String isbn;
    private String itemName;
    private BigDecimal price;
    private int quantity;
    private OrderEntity orderEntity;
}
