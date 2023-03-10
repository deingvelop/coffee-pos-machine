package com.demo.coffeePosMachine.beverage;

import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Id;
import java.time.LocalDate;

@Getter
//@RedisHash(value = "PopularBeverage", timeToLive = 60 * 60 * 24)
@RedisHash(value = "popularBeverage")
public class PopularBeverage {
    @Id
    private Long id;

    private Long beverageId;

    private String beverageName;

    private int beveragePrice;

    private Long count;

    @Indexed
    private LocalDate cachedDate;

    public PopularBeverage(PopularBeverageDto dto) {
       beverageId = dto.getBeverage_id();
       beverageName = dto.getBeverage_name();
       beveragePrice = dto.getBeverage_price();
       count = dto.getCount();
       cachedDate = LocalDate.now();
    }

}