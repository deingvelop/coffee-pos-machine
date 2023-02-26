package com.demo.coffeePosMachine.dto;

import lombok.Getter;

@Getter
public class BeverageDto {
    private final Long beverageId;
    private final String name;
    private final Long price;

    public BeverageDto(Long id, String name, Long price) {
        this.beverageId = id;
        this.name = name;
        this.price = price;
    }
}
