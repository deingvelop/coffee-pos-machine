package com.demo.coffeePosMachine.service;

import com.demo.coffeePosMachine.dto.UserDto;
import com.demo.coffeePosMachine.entity.User;
import com.demo.coffeePosMachine.exception.BusinessException;
import com.demo.coffeePosMachine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.demo.coffeePosMachine.exception.ErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserDto getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(USER_NOT_FOUND))
                .toDto();
    }

    @Override
    public void payForOrder(Long userId, Long beveragePrice) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(USER_NOT_FOUND));
        user.usePoint(beveragePrice);
    }

}
