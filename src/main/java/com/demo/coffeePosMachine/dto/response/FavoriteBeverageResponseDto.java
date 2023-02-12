package com.demo.coffeePosMachine.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class FavoriteBeverageResponseDto {
    private List<FavoriteBeverageDto> favorites;

    public FavoriteBeverageResponseDto(List<FavoriteBeverageDto> beverages) {
        this.favorites = beverages;
    }
}