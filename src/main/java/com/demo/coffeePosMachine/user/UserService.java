package com.demo.coffeePosMachine.user;

import com.demo.coffeePosMachine.beverage.BeverageDto;

public interface UserService {
    UserDto getUser(Long userId);

    void payForOrder(Long userId, BeverageDto beverage);

    void payForOrderWithOptimisticLock(Long userId, Long beveragePrice);
}
