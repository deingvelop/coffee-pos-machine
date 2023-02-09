package com.demo.coffeePosMachine.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class AllBeverageResponseDto {
    private List<BeverageDto> beverages;

    public AllBeverageResponseDto(List<BeverageDto> beverages) {
        this.beverages = beverages;
    }
}
