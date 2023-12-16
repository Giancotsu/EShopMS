package com.eshop.order.service;

import com.eshop.order.dto.OrderItemsDto;
import com.eshop.order.dto.OrderRequest;
import com.eshop.order.dto.mapper.OrderItemsMapper;
import com.eshop.order.entity.OrderEntity;
import com.eshop.order.entity.OrderItems;
import com.eshop.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public String placeOrder(OrderRequest orderRequest){
        OrderEntity order = new OrderEntity();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderItems> orderItems = orderRequest.getOrderItemsDto().stream().map(request -> {
            request.setOrderEntity(order);
            return OrderItemsMapper.dtoToEntity(request);
        }).toList();
        order.setItemList(orderItems);

        OrderEntity orderEntity = orderRepository.save(order);
        return "order placed successfully";
    }
}
