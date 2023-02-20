package com.demo.coffeePosMachine.service;

import com.demo.coffeePosMachine.dto.request.OrderRequestDto;
import com.demo.coffeePosMachine.dto.response.BeverageDto;
import com.demo.coffeePosMachine.dto.response.OrderResponseDto;
import com.demo.coffeePosMachine.dto.response.PointResponseDto;
import com.demo.coffeePosMachine.entity.Beverage;
import com.demo.coffeePosMachine.entity.Order;
import com.demo.coffeePosMachine.entity.PointLog;
import com.demo.coffeePosMachine.entity.User;
import com.demo.coffeePosMachine.exception.BusinessException;
import com.demo.coffeePosMachine.repository.BeverageRepository;
import com.demo.coffeePosMachine.repository.OrderRepository;
import com.demo.coffeePosMachine.repository.PointLogRepository;
import com.demo.coffeePosMachine.repository.UserRepository;
import com.demo.coffeePosMachine.util.TableValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.demo.coffeePosMachine.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final BeverageRepository beverageRepository;
    private final PointLogRepository pointLogRepository;
    private final UserRepository userRepository;

    @Override
    public OrderResponseDto createOrder(OrderRequestDto requestDto) {
        Long userId = requestDto.getUserId();
        Long beverageId = requestDto.getBeverageId();

        // 1. Beverage - DB BEVERAGE 테이블 탐색을 통해 음료 가격 가져오기
        Beverage beverage = beverageRepository.findById(beverageId)
                .orElseThrow(() -> new BusinessException(BEVERAGE_NOT_FOUND));
        int beveragePrice = beverage.getPrice();

        // 2. User - 사용자 포인트 잔액 가져오기
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(USER_NOT_FOUND));

        // 3. User의 잔액과 Beverage의  Price 비교 후 업데이트 및 DB USER 테이블에 반영
        if (beveragePrice > user.getPoint()) {
            throw new BusinessException(OUT_OF_POINT);
        }
        user.usePoint(beveragePrice);
        userRepository.save(user);

        // 4. OrderLog - 객체 생성 및 DB ORDER 테이블에 저장
        Order orderLog = Order.builder()
                .userId(userId)
                .beverageId(beverageId)
                .beverageName(beverage.getName())
                .beveragePrice(beveragePrice)
                .build();
        orderRepository.save(orderLog);

        // 5. PointLog - 객체 생성 및 DB POINT_LOG 테이블에 저장
        PointLog pointLog = PointLog.builder()
                .userId(userId)
                .point((long) (beveragePrice))
                .consumeYn(TableValue.Y.getValue())
                .chargeYn(TableValue.N.getValue())
                .build();
        pointLogRepository.save(pointLog);

        // 6. Response 생성 및 반환
        BeverageDto orderResult = new BeverageDto(beverage);
        PointResponseDto pointResult = new PointResponseDto(user);

        return new OrderResponseDto(orderResult, pointResult);
    }
}
