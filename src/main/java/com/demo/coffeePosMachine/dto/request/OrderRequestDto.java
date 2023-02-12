package com.demo.coffeePosMachine.dto.request;

import lombok.Getter;

@Getter
public class OrderRequestDto {
    private Long userId;
    private Long beverageId;
}
