package com.demo.coffeePosMachine.beverage;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "인기 음료 DTO")
public interface FavoriteBeverageDto {

    @Schema(description = "음료 식별값(ID)")
    Long getBeverage_id();

    @Schema(description = "음료 이름")
    String getBeverage_name();

    @Schema(description = "음료 가격")
    int getBeverage_price();

    @Schema(description = "7일간 음료 주문 횟수")
    Long getCount();
}
