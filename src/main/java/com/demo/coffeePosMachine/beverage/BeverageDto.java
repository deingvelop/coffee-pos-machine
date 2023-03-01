package com.demo.coffeePosMachine.beverage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "음료 DTO")
public class BeverageDto {

    @Schema(description = "음료 식별값(ID)")
    private final Long beverageId;

    @Schema(description = "음료 이름")
    private final String name;

    @Schema(description = "음료 가격")
    private final Long price;

    public BeverageDto(Long id, String name, Long price) {
        this.beverageId = id;
        this.name = name;
        this.price = price;
    }
}
