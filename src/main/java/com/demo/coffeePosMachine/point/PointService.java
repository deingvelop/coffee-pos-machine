package com.demo.coffeePosMachine.point;

import com.demo.coffeePosMachine.beverage.BeverageDto;
import com.demo.coffeePosMachine.point.PointRequestDto;
import com.demo.coffeePosMachine.point.PointResponseDto;

public interface PointService {
    PointResponseDto updatePoint(PointRequestDto requestDto);

    void savePaymentLog(Long userId, BeverageDto beverageDto);
}
