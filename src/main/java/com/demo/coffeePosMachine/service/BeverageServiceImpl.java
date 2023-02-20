package com.demo.coffeePosMachine.service;

import com.demo.coffeePosMachine.dto.response.AllBeverageResponseDto;
import com.demo.coffeePosMachine.dto.response.BeverageDto;
import com.demo.coffeePosMachine.dto.response.FavoriteBeverageDto;
import com.demo.coffeePosMachine.dto.response.FavoriteBeverageResponseDto;
import com.demo.coffeePosMachine.entity.Beverage;
import com.demo.coffeePosMachine.repository.BeverageRepository;
import com.demo.coffeePosMachine.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BeverageServiceImpl implements BeverageService{
    private final BeverageRepository beverageRepository;
    private final OrderRepository orderRepository;

    @Override
    public AllBeverageResponseDto showAllBeverages() {
        List<Beverage> data = beverageRepository.findAll();
        List<BeverageDto> response = data.stream()
                .map(BeverageDto::new)
                .collect(Collectors.toList());
        return new AllBeverageResponseDto(response);
    }

    @Override
    public FavoriteBeverageResponseDto showFavoriteBeverages() {
        List<FavoriteBeverageDto> data = orderRepository.findFavorites();
        return new FavoriteBeverageResponseDto(data);
    }

}
