package com.demo.coffeePosMachine.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "point_log")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointLog {
    @Id
    private Long id;
}
