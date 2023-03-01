package com.demo.coffeePosMachine.beverage;

import com.demo.coffeePosMachine.beverage.*;
import com.demo.coffeePosMachine.exception.BusinessException;
import com.demo.coffeePosMachine.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.demo.coffeePosMachine.exception.ErrorCode.BEVERAGE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class BeverageServiceImpl implements BeverageService {
    private final BeverageRepository beverageRepository;
    private final OrderRepository orderRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BeverageDto> showAllBeverages() {
        return beverageRepository.findAll()
                .stream().map(Beverage::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<FavoriteBeverageDto> showFavoriteBeverages() {
        return orderRepository.findFavorites();
    }

    @Override
    public BeverageDto getBeverage(Long beverageId) {
        return beverageRepository.findById(beverageId)
                .orElseThrow(() -> new BusinessException(BEVERAGE_NOT_FOUND))
                .toDto();
    }

}
