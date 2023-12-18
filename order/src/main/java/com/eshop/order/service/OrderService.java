package com.eshop.order.service;

import com.eshop.order.dto.CheckCartRequest;
import com.eshop.order.dto.OrderRequest;
import com.eshop.order.dto.mapper.OrderItemsMapper;
import com.eshop.order.entity.OrderEntity;
import com.eshop.order.entity.OrderItems;
import com.eshop.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public String placeOrder(OrderRequest orderRequest){
        OrderEntity order = new OrderEntity();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderItems> orderItems = orderRequest.getOrderItemsDto().stream().map(request -> {
            request.setOrderEntity(order);
            return OrderItemsMapper.dtoToEntity(request);
        }).toList();
        order.setItemList(orderItems);

        List<CheckCartRequest> cartList = new ArrayList<>();
        orderItems.forEach(item -> {
            CheckCartRequest cartItem = new CheckCartRequest();
            cartItem.setIsbn(item.getIsbn());
            cartItem.setQuantity(item.getQuantity());
            cartList.add(cartItem);
        });

        //WebClient webClient = WebClient.create("http://ESHOPINVENTORY");
        CheckCartRequest[] result = webClientBuilder.build().post()
                .uri("http://ESHOPINVENTORY/inventory/cart-check")
                .bodyValue(cartList)
                .retrieve()
                .bodyToMono(CheckCartRequest[].class)
                .block();

        //System.out.println(Arrays.toString(result));

        if(result.length < 1){
            OrderEntity orderEntity = orderRepository.save(order);
            return "Order placed successfully:\n\n" + orderEntity;
        } else {
            return "These products is not in stock:\n\n" + Arrays.toString(result);
        }
    }
}
