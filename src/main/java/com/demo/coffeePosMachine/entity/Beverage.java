package com.demo.coffeePosMachine.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
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

    @Column
    private String name;

    @Column
    private Long price;
}
