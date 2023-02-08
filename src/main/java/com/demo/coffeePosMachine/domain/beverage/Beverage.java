package com.demo.coffeePosMachine.domain.beverage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "beverage")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Beverage {
    @Id
    private Long id;

}
