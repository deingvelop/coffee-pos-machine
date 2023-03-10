package com.demo.coffeePosMachine.concurrency;

import com.demo.coffeePosMachine.beverage.Beverage;
import com.demo.coffeePosMachine.beverage.BeverageRepository;
import com.demo.coffeePosMachine.order.OrderRepository;
import com.demo.coffeePosMachine.order.OrderRequestDto;
import com.demo.coffeePosMachine.order.OrderService;
import com.demo.coffeePosMachine.point.PointLogRepository;
import com.demo.coffeePosMachine.user.LockedUser;
import com.demo.coffeePosMachine.user.LockedUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@ActiveProfiles("test")
@SpringBootTest
//@DataJpaTest
//@Transactional
class OrderConcurrencyControlTest {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private LockedUserRepository userRepository;

    @Autowired
    private PointLogRepository pointLogRepository;

    @Autowired
    private BeverageRepository beverageRepository;

    @BeforeEach
    void setUp() {
        userRepository.save(new LockedUser(1L, 10000L));
        beverageRepository.save(new Beverage(1L, "아메리카노", 4800L));
    }

    @Test
    @DisplayName("낙관적락_동시에_주문할_경우_에러_발생_테스트")
    void orderConcurrencyTest_supplyAsync() throws ExecutionException, InterruptedException {
        // given
        OrderRequestDto request = new OrderRequestDto(1L, 1L);

        // when
        CompletableFuture<?> order1 = CompletableFuture.supplyAsync(() -> orderService.createOrderWithOptimisticLock(request));
        CompletableFuture<?> order2 = CompletableFuture.supplyAsync(() -> orderService.createOrderWithOptimisticLock(request));
        CompletableFuture<?> order3 = CompletableFuture.supplyAsync(() -> orderService.createOrderWithOptimisticLock(request));

        Exception result = new Exception();
        try {
            CompletableFuture.allOf(order1, order2, order3).get();
        } catch (ExecutionException e) {
            result = (Exception) e.getCause();
        }

        // then
        assert result instanceof OptimisticLockingFailureException; // optimistic lock을 재처리하는 로직이 있어야 함 - (로직 작성 까다로워질 수 있음. 롤백 처리도 생각해야..!). 혹은, pessimistic lock을 적용하는 것도 맞음.
        // 비관적 락 통해서 한 번 걸어보고, 레디스를 통해서도 걸어보기 (락만을 위해서는 띄우지는 않지만, 보통 다 띄우기 때문에....)
    }

}