package com.demo.coffeePosMachine.beverage;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BeverageService {

    List<BeverageDto> showAllBeverages();

    List<PopularBeverageDto> showPopularBeverages();

    @Transactional(readOnly = true)
    @Cacheable(value = "popularBeverage", key = "cachedDate", cacheManager = "cacheManager")
    List<PopularBeverageResponseDto> showPopularBeveragesWithCache();

    BeverageDto getBeverage(Long beverageId);
}
