package com.demo.coffeePosMachine.point;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/point")
@Tag(name = "포인트", description = "포인트 관련 api 입니다.")
public class PointController {
    private final PointService pointService;

    @PostMapping
    @Operation(summary = "포인트 충전하기", description = "사용자의 포인트를 충전합니다.")
    public ResponseEntity<?> updatePoint(@RequestBody PointRequestDto requestDto) {
        return ResponseEntity.ok().body(pointService.updatePoint(requestDto));
    }
}
