package com.demo.coffeePosMachine.point;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.Min;

@Getter
@Schema(description = "포인트 충전 요청 DTO")
public class PointRequestDto {

    @Schema(description = "사용자 식별값(ID)")
    private Long userId;

    @Schema(description = "충전할 포인트 값")
    @Min(0L)    // 이 객체의 validation
    private Long point;
}
