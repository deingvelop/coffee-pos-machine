package com.demo.coffeePosMachine.beverage;

import java.util.List;

public interface BeverageService {

    List<BeverageDto> showAllBeverages();

    List<PopularBeverageDto> showPopularBeverages();

    BeverageDto getBeverage(Long beverageId);
}
