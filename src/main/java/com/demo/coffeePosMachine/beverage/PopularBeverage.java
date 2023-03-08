package com.demo.coffeePosMachine.beverage;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Table;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "popularBeverage")
public class PopularBeverage {
    Long beverageId;

    String beverageName;

    int beveragePrice;

    Long count;
}