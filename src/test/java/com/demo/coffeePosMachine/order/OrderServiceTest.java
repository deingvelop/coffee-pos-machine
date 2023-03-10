package com.demo.coffeePosMachine.order;

import com.demo.coffeePosMachine.beverage.Beverage;
import com.demo.coffeePosMachine.beverage.BeverageRepository;
import com.demo.coffeePosMachine.exception.BusinessException;
import com.demo.coffeePosMachine.exception.ErrorCode;
import com.demo.coffeePosMachine.point.PointLog;
import com.demo.coffeePosMachine.point.PointLogRepository;
import com.demo.coffeePosMachine.point.PointLogType;
import com.demo.coffeePosMachine.user.User;
import com.demo.coffeePosMachine.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PointLogRepository pointLogRepository;

    @Autowired
    private BeverageRepository beverageRepository;

    private static final Long O_USER_ID = 1L;
    private static final Long O_BEVERAGE_ID= 1L;
    private static final String O_BEVERAGE_NAME= "아메리카노";
    private static final Long O_BEVERAGE_PRICE= 4800L;
    private static final Long FIRST_POINT = 5000L;

    @BeforeEach
    void setUp() {
        userRepository.save(new User(O_USER_ID, FIRST_POINT));
        beverageRepository.save(new Beverage(O_BEVERAGE_ID, O_BEVERAGE_NAME, O_BEVERAGE_PRICE));
    }

    @Test
    @DisplayName("정상적으로_주문하기_성공")
    void createOrderSuccess() {
        // given
        OrderRequestDto request = new OrderRequestDto(O_USER_ID, O_BEVERAGE_ID);

        // when
        OrderResponseDto response = orderService.createOrder(request);

        // then
        User user = userRepository.findById(O_USER_ID).get();
        Beverage beverage = beverageRepository.findById(O_BEVERAGE_ID).get();
        PointLog pointLog = (PointLog) pointLogRepository.findAll();
        assert user.getPoint() == FIRST_POINT - O_BEVERAGE_PRICE;
        assert pointLog.getPoint() == O_BEVERAGE_PRICE;
        assert pointLog.getUserId() == O_USER_ID;
        assert pointLog.getType() == PointLogType.PAY;

        assert response.getOrderId() == 1L;
        assert response.getBeverageId() == O_BEVERAGE_ID;
        assert response.getUserId() == O_USER_ID;
    }

    @Test
    @DisplayName("잔액_부족일 경우_오류_발생")
    void createOrderFail() {
        // given
        OrderRequestDto request = new OrderRequestDto(O_USER_ID, O_BEVERAGE_ID);
        orderService.createOrder(request);

        // when
        ErrorCode errorCode = null;
        try {
            OrderResponseDto response = orderService.createOrder(request);
        } catch (BusinessException e) {
            errorCode = e.getErrorCode();
        }

        // then
        assert errorCode == ErrorCode.OUT_OF_POINT;
    }

    @Test
    @DisplayName("없는_사용자의_경우_오류_발생")
    void createOrderFail_NoUser() {
        // given
        OrderRequestDto request = new OrderRequestDto(2L, O_BEVERAGE_ID);

        // when
        ErrorCode errorCode = null;
        try {
            OrderResponseDto response = orderService.createOrder(request);
        } catch (BusinessException e) {
            errorCode = e.getErrorCode();
        }

        // then
        assert errorCode == ErrorCode.USER_NOT_FOUND;
    }

    @Test
    @DisplayName("없는_음료의_경우_오류_발생")
    void createOrderFail_NoBeverage() {
        // given
        OrderRequestDto request = new OrderRequestDto(O_USER_ID, 2L);

        // when
        ErrorCode errorCode = null;
        try {
            OrderResponseDto response = orderService.createOrder(request);
        } catch (BusinessException e) {
            errorCode = e.getErrorCode();
        }

        // then
        assert errorCode == ErrorCode.BEVERAGE_NOT_FOUND;
    }

    @Test
    void createOrderWithOptimisticLock() {
    }
}