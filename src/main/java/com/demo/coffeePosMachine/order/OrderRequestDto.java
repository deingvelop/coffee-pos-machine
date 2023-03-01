package com.demo.coffeePosMachine.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "음료 주문 요청 DTO")
public class OrderRequestDto {

    @Schema(description = "사용자 식별값(ID)")
    private Long userId;

    @Schema(description = "음료 식별값(ID)")
    private Long beverageId;
}
