package com.demo.coffeePosMachine.domain.point;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "user_point_log")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPointLog {
    @Id
    private Long id;
}
