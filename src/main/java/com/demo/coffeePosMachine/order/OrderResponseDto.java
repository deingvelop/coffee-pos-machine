package com.demo.coffeePosMachine.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Schema(description = "음료 주문 응답 DTO")
public class OrderResponseDto {

    @Schema(description = "주문 식별값(ID)")
    private Long orderId;

    @Schema(description = "사용자 식별값(ID)")
    private Long userId;

    @Schema(description = "음료 식별값(ID)")
    private Long beverageId;

    @Schema(description = "음료 이름")
    private String beverageName;

    @Schema(description = "음료 가격")
    private Long beveragePrice;

    @Schema(description = "주문 생성 시각")
    private LocalDateTime createdAt;
}
