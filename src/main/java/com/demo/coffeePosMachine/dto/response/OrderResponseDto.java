package com.demo.coffeePosMachine.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderResponseDto {
    private BeverageDto orderResult;
    private PointResponseDto pointResult;

}
