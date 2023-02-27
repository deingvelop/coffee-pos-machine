package com.demo.coffeePosMachine.service;

import com.demo.coffeePosMachine.dto.request.OrderRequestDto;
import com.demo.coffeePosMachine.entity.Beverage;
import com.demo.coffeePosMachine.entity.User;
import com.demo.coffeePosMachine.repository.BeverageRepository;
import com.demo.coffeePosMachine.repository.OrderRepository;
import com.demo.coffeePosMachine.repository.UserRepository;
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
class OrderServiceTest {
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

//    @AfterEach
//    void tearDown() {
//    }

    @Test
    @DisplayName("동시성_이슈_발생시키기")
    void concurrencyTest() throws ExecutionException, InterruptedException {
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
    void createOrder() {
    }
}