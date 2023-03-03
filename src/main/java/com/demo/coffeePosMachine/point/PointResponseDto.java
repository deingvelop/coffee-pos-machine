package com.demo.coffeePosMachine.point;

import com.demo.coffeePosMachine.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "포인트 충전 응답 DTO")
public class PointResponseDto {

    @Schema(description = "사용자 식별값(ID)")
    private Long userId;

    @Schema(description = "사용자의 업데이트된 포인트")
    private Long point;

    public PointResponseDto(User user) {
        this.userId = user.getId();
        this.point = user.getPoint();
    }
}
