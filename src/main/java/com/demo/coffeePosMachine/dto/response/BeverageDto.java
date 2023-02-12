package com.demo.coffeePosMachine.dto.response;

import com.demo.coffeePosMachine.entity.Beverage;
import lombok.Getter;

@Getter
public class BeverageDto {
    private final Long beverageId;
    private final String name;
    private final int price;

    public BeverageDto(Beverage beverage) {
        this.beverageId = beverage.getId();
        this.name = beverage.getName();
        this.price = beverage.getPrice();
    }
}
