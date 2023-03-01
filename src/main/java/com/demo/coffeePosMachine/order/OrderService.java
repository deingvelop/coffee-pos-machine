package com.demo.coffeePosMachine.order;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto requestDto);
    OrderResponseDto createOrderWithOptimisticLock(OrderRequestDto requestDto);
}