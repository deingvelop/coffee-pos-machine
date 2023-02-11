package com.demo.coffeePosMachine.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class AllBeverageResponseDto {
    private List<BeverageDto> beverages;

    public AllBeverageResponseDto(List<BeverageDto> beverages) {
        this.beverages = beverages;
    }
}
