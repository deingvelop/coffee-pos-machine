package com.demo.coffeePosMachine.service;

import com.demo.coffeePosMachine.dto.request.OrderRequestDto;
import com.demo.coffeePosMachine.dto.response.OrderResponseDto;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto requestDto);
}