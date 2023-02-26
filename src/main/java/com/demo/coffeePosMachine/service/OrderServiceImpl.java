package com.demo.coffeePosMachine.service;

import com.demo.coffeePosMachine.dto.BeverageDto;
import com.demo.coffeePosMachine.dto.request.OrderRequestDto;
import com.demo.coffeePosMachine.dto.response.OrderResponseDto;
import com.demo.coffeePosMachine.entity.Order;
import com.demo.coffeePosMachine.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final BeverageService beverageService;
    private final UserService userService;
    private final PointService pointService;

    @Override
    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto requestDto) {
        // 1. 음료 주문하기
        BeverageDto beverage = beverageService.getBeverage(requestDto.getBeverageId());
        Order order = orderRepository.save(new Order(requestDto, beverage));
        // 2. 포인트 반영 및 로그 남기기
        userService.payForOrder(requestDto.getUserId(), order.getBeveragePrice());
        pointService.savePaymentLog(requestDto.getUserId(), beverage);;
        // 3. 응답 리턴하기
        return order.toResponseDto();
    }
}
