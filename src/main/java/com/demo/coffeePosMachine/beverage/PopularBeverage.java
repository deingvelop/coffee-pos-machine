package com.demo.coffeePosMachine.beverage;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "popularBeverage")
public class PopularBeverage implements Serializable {
    Long beverageId;

    String beverageName;

    int beveragePrice;

    Long count;

    @CreationTimestamp
    LocalDate cachedDate = LocalDate.now();

    public PopularBeverageResponseDto toDto() {
        return new PopularBeverageResponseDto(beverageId, beverageName, beveragePrice, count, cachedDate);
    }
}