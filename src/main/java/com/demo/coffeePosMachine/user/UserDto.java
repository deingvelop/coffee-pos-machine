package com.demo.coffeePosMachine.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "사용자 DTO")
public class UserDto {

    @Schema(description = "사용자 식별값(ID)")
    private Long userId;

    @Schema(description = "사용자 포인트값")
    private Long userPoint;
}
