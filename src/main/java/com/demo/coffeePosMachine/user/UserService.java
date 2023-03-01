package com.demo.coffeePosMachine.user;

import com.demo.coffeePosMachine.user.UserDto;

public interface UserService {
    UserDto getUser(Long userId);

    void payForOrder(Long userId, Long beveragePrice);
}
