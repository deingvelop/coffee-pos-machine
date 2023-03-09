package com.demo.coffeePosMachine.util;

import com.demo.coffeePosMachine.beverage.PopularBeverage;
import com.demo.coffeePosMachine.beverage.PopularBeverageDto;
import com.demo.coffeePosMachine.beverage.PopularBeverageRepository;
import com.demo.coffeePosMachine.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CacheLoader implements CommandLineRunner {
    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final PopularBeverageRepository popularBeverageRepository;

    @Override
    @Cacheable(value = "popularBeverage", cacheManager = "cacheManager", unless = "#result == null")
    public void run(String... args) {
        List<PopularBeverageDto> data = orderRepository.findPopulars();
        List<PopularBeverage> cachingData = data.stream()
                .map(PopularBeverage::new)
                .collect(Collectors.toList());
        popularBeverageRepository.saveAll(cachingData);
    }
}
