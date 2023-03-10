package com.demo.coffeePosMachine.order;

import com.demo.coffeePosMachine.aop.SendOrderLog;
import com.demo.coffeePosMachine.beverage.BeverageDto;
import com.demo.coffeePosMachine.beverage.BeverageService;
import com.demo.coffeePosMachine.point.PointService;
import com.demo.coffeePosMachine.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final BeverageService beverageService;
    private final UserService userService;
    private final PointService pointService;

    @Override
    @Transactional
    @SendOrderLog
    public OrderResponseDto createOrder(OrderRequestDto requestDto) {
        // 1. 음료 주문하기
        BeverageDto beverage = beverageService.getBeverage(requestDto.getBeverageId());
        Order order = orderRepository.save(new Order(requestDto, beverage));
        // 2. order 로그 남기기
        userService.payForOrder(requestDto.getUserId(), order.getBeveragePrice());
        // 3. 포인트 반영 및 로그 남기기
        pointService.savePaymentLog(requestDto.getUserId(), beverage);
        // 4. 응답 리턴하기
        return order.toResponseDto();
    }

    @Override
    @Transactional
    public OrderResponseDto createOrderWithOptimisticLock(OrderRequestDto requestDto) {
        // 1. 음료 주문하기
        BeverageDto beverage = beverageService.getBeverage(requestDto.getBeverageId());
        Order order = orderRepository.save(new Order(requestDto, beverage));
        // 2. order 로그 남기기
        userService.payForOrderWithOptimisticLock(requestDto.getUserId(), order.getBeveragePrice());
        // 3. 포인트 반영 및 로그 남기기
        pointService.savePaymentLog(requestDto.getUserId(), beverage);
        // 4. 응답 리턴하기
        return order.toResponseDto();
    }
}
