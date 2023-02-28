package com.demo.coffeePosMachine.order;

import com.demo.coffeePosMachine.order.OrderRequestDto;
import com.demo.coffeePosMachine.order.OrderResponseDto;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto requestDto);
}