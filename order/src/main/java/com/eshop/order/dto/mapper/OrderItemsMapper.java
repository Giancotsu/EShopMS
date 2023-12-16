package com.eshop.order.dto.mapper;

import com.eshop.order.dto.OrderItemsDto;
import com.eshop.order.entity.OrderItems;

public class OrderItemsMapper {

    public static OrderItems dtoToEntity(OrderItemsDto orderItemsDto){
        OrderItems orderItems = new OrderItems();
        orderItems.setOrderItemsId(orderItemsDto.getOrderItemsId());
        orderItems.setItemName(orderItemsDto.getItemName());
        orderItems.setPrice(orderItemsDto.getPrice());
        orderItems.setQuantity(orderItemsDto.getQuantity());
        orderItems.setOrderEntity(orderItemsDto.getOrderEntity());

        return orderItems;
    }

    public static OrderItemsDto entityToDto(OrderItems orderItems){
        OrderItemsDto orderItemsDto = new OrderItemsDto();
        orderItemsDto.setOrderItemsId(orderItems.getOrderItemsId());
        orderItemsDto.setItemName(orderItems.getItemName());
        orderItemsDto.setPrice(orderItems.getPrice());
        orderItemsDto.setQuantity(orderItems.getQuantity());
        orderItemsDto.setOrderEntity(orderItems.getOrderEntity());

        return orderItemsDto;
    }
}
