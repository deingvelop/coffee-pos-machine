package com.demo.coffeePosMachine.dto.response;

import com.demo.coffeePosMachine.entity.Beverage;
import lombok.Getter;

@Getter
public class FavoriteBeverageDto {
    private final Long beverageId;
    private final String name;
    private final Long price;
    private final Long count;

    public FavoriteBeverageDto(Beverage beverage, Long count) {
        this.beverageId = beverage.getId();
        this.name = beverage.getName();
        this.price = beverage.getPrice();
        this.count = count;
    }
}
