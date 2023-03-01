package com.demo.coffeePosMachine.point;

import com.demo.coffeePosMachine.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PointResponseDto {
    private Long userId;
    private Long point;

    public PointResponseDto(User user) {
        this.userId = user.getId();
        this.point = user.getPoint();
    }
}
