package com.demo.coffeePosMachine.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderRequestDto {
    private Long userId;
    private Long beverageId;
}
