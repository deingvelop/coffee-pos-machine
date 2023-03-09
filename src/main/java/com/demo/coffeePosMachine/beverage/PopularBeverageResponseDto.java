package com.demo.coffeePosMachine.beverage;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Table;
import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Getter
@AllArgsConstructor
@Table(name = "popularBeverage")
public class PopularBeverageResponseDto {
    Long beverageId;

    String beverageName;

    int beveragePrice;

    Long count;

    @CreationTimestamp
    LocalDate cachedDate = LocalDate.now();
}