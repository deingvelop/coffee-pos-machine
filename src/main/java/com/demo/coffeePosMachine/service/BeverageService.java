package com.demo.coffeePosMachine.service;

import com.demo.coffeePosMachine.dto.BeverageDto;
import com.demo.coffeePosMachine.dto.response.FavoriteBeverageDto;

import java.util.List;

public interface BeverageService {

    List<BeverageDto> showAllBeverages();

    List<FavoriteBeverageDto> showFavoriteBeverages();

    BeverageDto getBeverage(Long beverageId);
}
