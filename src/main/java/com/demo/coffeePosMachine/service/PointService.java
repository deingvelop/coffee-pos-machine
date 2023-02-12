package com.demo.coffeePosMachine.service;

import com.demo.coffeePosMachine.dto.request.PointRequestDto;
import com.demo.coffeePosMachine.dto.response.PointResponseDto;

public interface PointService {
    PointResponseDto updatePoint(PointRequestDto requestDto);
}
