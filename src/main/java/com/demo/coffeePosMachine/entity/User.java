package com.demo.coffeePosMachine.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "user")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long point;

    public void changePoint(Long chargingPoint) {
        this.point += chargingPoint;
    }

    public void usePoint(int usingPoint) {
        this.point -= usingPoint;
    }
}
