package com.demo.coffeePosMachine.point;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/point")
public class PointController {
    private final PointService pointService;

    @PostMapping
    public ResponseEntity<?> updatePoint(@RequestBody PointRequestDto requestDto) {
        return ResponseEntity.ok().body(pointService.updatePoint(requestDto));
    }
}
