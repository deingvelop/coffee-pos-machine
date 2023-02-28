package com.demo.coffeePosMachine;

import com.demo.coffeePosMachine.order.OrderRequestDto;
import com.demo.coffeePosMachine.beverage.Beverage;
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
    private BeverageRepository beverageRepository;

    @BeforeEach
    void setUp() {
        userRepository.save(new User(1L, 10000L));
        beverageRepository.save(new Beverage(1L, "아메리카노", 4800L));
    }

    @Test
    @DisplayName("동시성_이슈가_발생하는지_테스트_1")
    void concurrencyTest_supplyAsync() throws ExecutionException, InterruptedException {
        // given
        OrderRequestDto request = new OrderRequestDto(1L, 1L);

        // when
        CompletableFuture<?> order1 = CompletableFuture.supplyAsync(() -> orderService.createOrder(request));
        CompletableFuture<?> order2 = CompletableFuture.supplyAsync(() -> orderService.createOrder(request));
        CompletableFuture<?> order3 = CompletableFuture.supplyAsync(() -> orderService.createOrder(request));
        CompletableFuture.allOf(order1, order2, order3).get();

        // then
        assert userRepository.findById(1L).get().getPoint() != 400;
        assert userRepository.findById(1L).get().getPoint() == 5200;
    }

    @Test
    @DisplayName("동시성_이슈가_발생하는지_테스트_2")
    void concurrencyTest_runAsync() throws ExecutionException, InterruptedException {
        // given
        OrderRequestDto request = new OrderRequestDto(1L, 1L);

        // when
        CompletableFuture<?> order1 = CompletableFuture.runAsync(() -> orderService.createOrder(request));
        CompletableFuture<?> order2 = CompletableFuture.runAsync(() -> orderService.createOrder(request));
        CompletableFuture<?> order3 = CompletableFuture.runAsync(() -> orderService.createOrder(request));
        CompletableFuture.allOf(order1, order2, order3).get();

        // then
        assert userRepository.findById(1L).get().getPoint() != 400;
        assert userRepository.findById(1L).get().getPoint() == 5200;
    }

}