package com.demo.coffeePosMachine.point;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PointRequestDto {
    private Long userId;
    private Long point;
}
