package com.demo.coffeePosMachine.user;

public interface UserService {
    UserDto getUser(Long userId);

    void payForOrder(Long userId, Long beveragePrice);

    void payForOrderWithOptimisticLock(Long userId, Long beveragePrice);
}
