package com.demo.coffeePosMachine.concurrency;

import com.demo.coffeePosMachine.order.OrderRequestDto;
import com.demo.coffeePosMachine.beverage.Beverage;
import com.demo.coffeePosMachine.point.PointLogRepository;
import com.demo.coffeePosMachine.user.User;
import com.demo.coffeePosMachine.beverage.BeverageRepository;
import com.demo.coffeePosMachine.order.OrderRepository;
import com.demo.coffeePosMachine.user.UserRepository;
import com.demo.coffeePosMachine.order.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@SpringBootTest
//@DataJpaTest
//@Transactional
class OrderConcurrencyTest {
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

    @BeforeEach
    void setUp() {
        userRepository.save(new User(1L, 10000L));
        beverageRepository.save(new Beverage(1L, "아메리카노", 4800L));
    }

    @Test
    @DisplayName("동시에_주문할_경우_적용_안됨_테스트")
    void orderConcurrencyTest_supplyAsync() throws ExecutionException, InterruptedException {
        // given
        OrderRequestDto request = new OrderRequestDto(1L, 1L);

        // when
        CompletableFuture<?> order1 = CompletableFuture.supplyAsync(() -> orderService.createOrder(request));
        CompletableFuture<?> order2 = CompletableFuture.supplyAsync(() -> orderService.createOrder(request));
        CompletableFuture.allOf(order1, order2).get();

        // then
        assert userRepository.findById(1L).get().getPoint() != 400;
        assert userRepository.findById(1L).get().getPoint() == 5200;

        assertThat(pointLogRepository.findById(1L).get()).isNotNull();
        assertThat(pointLogRepository.findById(2L).get()).isNull();
    }

    @Test
    @DisplayName("동시에_주문할_경우_적용_안됨_테스트")
    void orderConcurrencyTest_runAsync() throws ExecutionException, InterruptedException {
        // given
        OrderRequestDto request = new OrderRequestDto(1L, 1L);

        // when
        CompletableFuture<?> order1 = CompletableFuture.runAsync(() -> orderService.createOrder(request));
        CompletableFuture<?> order2 = CompletableFuture.runAsync(() -> orderService.createOrder(request));
        CompletableFuture.allOf(order1, order2).get();

        // then
        // userRepository 포인트 반영 테스트
        assert userRepository.findById(1L).get().getPoint() != 400;
        assert userRepository.findById(1L).get().getPoint() == 5200;

        // pointLogRepository 포인트 로그 쌓이는지 테스트
        assert pointLogRepository.findAll().size() != 2;
        assert pointLogRepository.findAll().size() == 1;
    }

}