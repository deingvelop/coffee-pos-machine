package com.demo.coffeePosMachine.entity;

import com.demo.coffeePosMachine.dto.BeverageDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "beverage")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Beverage {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Long price;

    public BeverageDto toDto() {
        return new BeverageDto(id, name, price);
    }
}
