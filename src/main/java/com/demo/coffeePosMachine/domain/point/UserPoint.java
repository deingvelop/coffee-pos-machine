package com.demo.coffeePosMachine.domain.point;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "user_point")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPoint {
    @Id
    private Long id;

}
