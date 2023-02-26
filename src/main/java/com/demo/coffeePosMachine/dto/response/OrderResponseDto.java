package com.demo.coffeePosMachine.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OrderResponseDto {
    private Long orderId;

    private Long userId;

    private Long beverageId;

    private String beverageName;

    private Long beveragePrice;

    private LocalDateTime createdAt;
}
