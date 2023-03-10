package com.demo.coffeePosMachine.beverage;

import java.util.List;

public interface BeverageService {

    List<BeverageDto> showAllBeverages();

    List<PopularBeverageDto> showPopularBeverages();

//    List<PopularBeverageResponseDto> showPopularBeveragesWithCache();

    List<PopularBeverage> showPopularBeveragesWithCache();

    BeverageDto getBeverage(Long beverageId);
}
