package com.demo.coffeePosMachine.service;

import com.demo.coffeePosMachine.dto.response.AllBeverageResponseDto;
import com.demo.coffeePosMachine.dto.response.FavoriteBeverageResponseDto;

public interface BeverageService {

    AllBeverageResponseDto showAllBeverages();

    FavoriteBeverageResponseDto showFavoriteBeverages();
}
