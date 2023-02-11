package com.demo.coffeePosMachine.service;

import com.demo.coffeePosMachine.dto.response.AllBeverageResponseDto;
import com.demo.coffeePosMachine.dto.response.BeverageDto;
import com.demo.coffeePosMachine.dto.response.FavoriteBeverageResponseDto;
import com.demo.coffeePosMachine.entity.Beverage;
import com.demo.coffeePosMachine.repository.BeverageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BeverageServiceImpl implements BeverageService{
    private final BeverageRepository beverageRepository;

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
        List<Beverage> data = beverageRepository.findFavorites();
        List<BeverageDto> response = data.stream()
                .map(BeverageDto::new)
                .collect(Collectors.toList());
        return new FavoriteBeverageResponseDto(response);
    }

}
