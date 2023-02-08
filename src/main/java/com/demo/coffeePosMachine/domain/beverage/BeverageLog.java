package com.demo.coffeePosMachine.domain.beverage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "beverage_log")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeverageLog {
    @Id
    private Long id;
}
