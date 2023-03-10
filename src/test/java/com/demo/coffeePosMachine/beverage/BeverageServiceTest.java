package com.demo.coffeePosMachine.beverage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
class BeverageServiceTest {

    @Autowired
    private BeverageService beverageService;

    @Autowired
    private BeverageRepository beverageRepository;

    @BeforeEach
    void setUp() {
        beverageRepository.save(new Beverage(1L, "아메리카노", 4800L));
        beverageRepository.save(new Beverage(2L, "카페라떼", 5200L));
        beverageRepository.save(new Beverage(3L, "아포가토", 5600L));
    }

    @Test
    @DisplayName("모든_음료_조회하기_테스트")
    void showAllBeverages() {
        // when
        List<?> responses = beverageService.showAllBeverages();

        // then
        assert responses.size() == 3;
    }

    @Test
    void showFavoriteBeverages() {

    }
}