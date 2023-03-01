package com.demo.coffeePosMachine.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderRequestDto {
    private Long userId;
    private Long beverageId;
}
