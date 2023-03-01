package com.demo.coffeePosMachine.beverage;

import com.demo.coffeePosMachine.beverage.BeverageDto;
import com.demo.coffeePosMachine.beverage.FavoriteBeverageDto;

import java.util.List;

public interface BeverageService {

    List<BeverageDto> showAllBeverages();

    List<FavoriteBeverageDto> showFavoriteBeverages();

    BeverageDto getBeverage(Long beverageId);
}
