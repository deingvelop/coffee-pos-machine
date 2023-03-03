package com.demo.coffeePosMachine.concurrency;

import com.demo.coffeePosMachine.beverage.BeverageRepository;
import com.demo.coffeePosMachine.point.PointLogRepository;
import com.demo.coffeePosMachine.point.PointRequestDto;
import com.demo.coffeePosMachine.point.PointService;
import com.demo.coffeePosMachine.user.User;
import com.demo.coffeePosMachine.user.UserRepository;
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
class PointConcurrencyTest {
    @Autowired
    private PointService pointService;

    @Autowired
    private PointLogRepository pointLogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BeverageRepository beverageRepository;

    @BeforeEach
    void setUp() {
        userRepository.save(new User(1L, 0L));
    }

    @Test
    @DisplayName("동시에_포인트_충전할_경우_적용_안됨_테스트")
    void pointConcurrencyTest() throws ExecutionException, InterruptedException {
        // given
        PointRequestDto request = new PointRequestDto(1L, 10000L);

        // when
        CompletableFuture<?> order1 = CompletableFuture.supplyAsync(() -> pointService.updatePoint(request));
        CompletableFuture<?> order2 = CompletableFuture.supplyAsync(() -> pointService.updatePoint(request));
        CompletableFuture.allOf(order1, order2).get();

        // then
        // userRepository 포인트 반영 테스트 (통과되면 반영 안 되는 것)
        assert userRepository.findById(1L).get().getPoint() != 20000L;
        assert userRepository.findById(1L).get().getPoint() == 10000L;

    }

    @Test
    @DisplayName("동시에_충전할_경우_포인트로그_테스트")
    void pointLogConcurrencyTest() throws ExecutionException, InterruptedException {
        // given
        PointRequestDto request = new PointRequestDto(1L, 10000L);

        // when
        CompletableFuture<?> order1 = CompletableFuture.supplyAsync(() -> pointService.updatePoint(request));
        CompletableFuture<?> order2 = CompletableFuture.supplyAsync(() -> pointService.updatePoint(request));
        CompletableFuture.allOf(order1, order2).get();

        // then
        // pointLogRepository 포인트 로그 쌓임 테스트
        assert pointLogRepository.findAll().size() == 2;  // 통과됨
//        assert pointLogRepository.findAll().size() != 2;    // 실패함. 즉, 동시성 이슈 발생해도 메서드는 제대로 실행이 되어서, 로그는 insert로 제대로 쌓임
    }

}