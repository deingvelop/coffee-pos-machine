package com.demo.coffeePosMachine.service;

import com.demo.coffeePosMachine.dto.UserDto;

public interface UserService {
    UserDto getUser(Long userId);

    void payForOrder(Long userId, Long beveragePrice);
}
