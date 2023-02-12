package com.demo.coffeePosMachine.dto.response;

import com.demo.coffeePosMachine.entity.User;
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
