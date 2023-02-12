package com.demo.coffeePosMachine.service;

import com.demo.coffeePosMachine.dto.request.OrderRequestDto;
import com.demo.coffeePosMachine.dto.response.BeverageDto;
import com.demo.coffeePosMachine.dto.response.OrderResponseDto;
import com.demo.coffeePosMachine.dto.response.PointResponseDto;
import com.demo.coffeePosMachine.entity.Beverage;
import com.demo.coffeePosMachine.entity.OrderLog;
import com.demo.coffeePosMachine.entity.PointLog;
import com.demo.coffeePosMachine.entity.User;
import com.demo.coffeePosMachine.exception.BusinessException;
import com.demo.coffeePosMachine.repository.BeverageRepository;
import com.demo.coffeePosMachine.repository.OrderLogRepository;
import com.demo.coffeePosMachine.repository.PointLogRepository;
import com.demo.coffeePosMachine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.demo.coffeePosMachine.exception.ErrorCode.BEVERAGE_NOT_FOUND;
import static com.demo.coffeePosMachine.exception.ErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderLogRepository orderLogRepository;
    private final BeverageRepository beverageRepository;
    private final PointLogRepository pointLogRepository;
    private final UserRepository userRepository;

    @Override
    public OrderResponseDto createOrder(OrderRequestDto requestDto) {
        Long userId = requestDto.getUserId();
        Long beverageId = requestDto.getBeverageId();

        // 1. OrderLog - 객체 생성 및 DB ORDER 테이블에 저장
        OrderLog orderLog = OrderLog.builder()
                .userId(userId)
                .beverageId(beverageId)
                .createdAt(LocalDateTime.now())
                .build();
        orderLogRepository.save(orderLog);

        // 2. Beverage - DB BEVERAGE 테이블 탐색을 통해 음료 가격 가져오기
        Beverage beverage = beverageRepository.findById(beverageId)
                .orElseThrow(() -> new BusinessException(BEVERAGE_NOT_FOUND));
        int beveragePrice = beverage.getPrice();

        // 3. PointLog - 객체 생성 및 DB POINT_LOG 테이블에 저장
        PointLog pointLog = PointLog.builder()
                .userId(userId)
                .point((long) (-1 * beveragePrice))
                .build();
        pointLogRepository.save(pointLog);

        // 4. User - 사용자 포인트 상태 업데이트 및 DB USER 테이블에 반영
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(USER_NOT_FOUND));
        user.usePoint(beveragePrice);
        userRepository.save(user);

        // 5. Response 생성 및 반환
        BeverageDto orderResult = new BeverageDto(beverage);
        PointResponseDto pointResult = new PointResponseDto(user);

        return new OrderResponseDto(orderResult, pointResult);
    }
}
