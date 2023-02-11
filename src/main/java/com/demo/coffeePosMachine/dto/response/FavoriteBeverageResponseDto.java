package com.demo.coffeePosMachine.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class FavoriteBeverageResponseDto {
    private List<BeverageDto> favorites;

    public FavoriteBeverageResponseDto(List<BeverageDto> beverages) {
        this.favorites = beverages;
    }
}