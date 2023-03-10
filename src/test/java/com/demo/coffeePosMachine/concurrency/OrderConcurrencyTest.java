package com.demo.coffeePosMachine.concurrency;

import com.demo.coffeePosMachine.beverage.Beverage;
import com.demo.coffeePosMachine.beverage.BeverageRepository;
import com.demo.coffeePosMachine.order.OrderRepository;
import com.demo.coffeePosMachine.order.OrderRequestDto;
import com.demo.coffeePosMachine.order.OrderService;
import com.demo.coffeePosMachine.point.PointLogRepository;
import com.demo.coffeePosMachine.user.User;
import com.demo.coffeePosMachine.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@ActiveProfiles("test")
@SpringBootTest
//@DataJpaTest
@Transactional
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
    @DisplayName("동시에_주문할_경우_적용_안됨_supplyAsync_테스트")
    void orderConcurrencyTest_supplyAsync() throws ExecutionException, InterruptedException {
        // given
        OrderRequestDto request = new OrderRequestDto(1L, 1L);

        // when
        CompletableFuture<?> order1 = CompletableFuture.supplyAsync(() -> orderService.createOrder(request));   // 트랜잭션은 스레드에 종속적임. 따라서 다른 트랜잭션이 열림
        CompletableFuture<?> order2 = CompletableFuture.supplyAsync(() -> orderService.createOrder(request));
        CompletableFuture.allOf(order1, order2).get();

        // then
        // userRepository 포인트 반영 테스트 (통과되면 반영 안 되는 것)
        assert userRepository.findById(1L).get().getPoint() == 400;     // 이게 필요함

    }

    @Test
    @DisplayName("동시에_주문할_경우_테스트")
    void orderConcurrencyTest_runAsync() throws ExecutionException, InterruptedException {
        // given
        OrderRequestDto request = new OrderRequestDto(1L, 1L);

        // when
        CompletableFuture<?> order1 = CompletableFuture.runAsync(() -> orderService.createOrder(request));
        CompletableFuture<?> order2 = CompletableFuture.runAsync(() -> orderService.createOrder(request));
        CompletableFuture.allOf(order1, order2).get();

        // then
        // userRepository 포인트 반영 테스트 (통과되면 반영 안 되는 것)
        assert userRepository.findById(1L).get().getPoint() != 400;
        assert userRepository.findById(1L).get().getPoint() == 5200;

    }

    @Test
    @DisplayName("동시에_주문할_경우_포인트로그_테스트")
    void pointLogConcurrencyTest() throws ExecutionException, InterruptedException {
        // given
        OrderRequestDto request = new OrderRequestDto(1L, 1L);

        // when
        CompletableFuture<?> order1 = CompletableFuture.supplyAsync(() -> orderService.createOrder(request));
        CompletableFuture<?> order2 = CompletableFuture.supplyAsync(() -> orderService.createOrder(request));
        CompletableFuture.allOf(order1, order2).get();

        // then
        // pointLogRepository 포인트 로그 쌓임 테스트
        assert pointLogRepository.findAll().size() == 2;  // 통과됨
//        assert pointLogRepository.findAll().size() != 2;    // 실패함. 즉, 동시성 이슈 발생해도 메서드는 제대로 실행이 되어서, 로그는 insert로 제대로 쌓임

    }

}