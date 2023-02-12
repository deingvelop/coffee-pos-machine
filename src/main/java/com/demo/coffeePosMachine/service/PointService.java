package com.demo.coffeePosMachine.service;

import com.demo.coffeePosMachine.dto.request.PointRequestDto;
import com.demo.coffeePosMachine.dto.response.PointResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface PointService {
    PointResponseDto updatePoint(PointRequestDto requestDto);
}
