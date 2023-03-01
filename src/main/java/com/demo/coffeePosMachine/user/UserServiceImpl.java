package com.demo.coffeePosMachine.user;

import com.demo.coffeePosMachine.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.demo.coffeePosMachine.exception.ErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final LockedUserRepository lockedUserRepository;

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

    @Override
    public void payForOrderWithOptimisticLock(Long userId, Long beveragePrice) {
        LockedUser user = lockedUserRepository.findByIdWithOptimisticLock(userId)
                .orElseThrow(() -> new BusinessException(USER_NOT_FOUND));
        user.usePoint(beveragePrice);
    }

}
