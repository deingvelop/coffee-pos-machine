package com.demo.coffeePosMachine.user;

import com.demo.coffeePosMachine.beverage.BeverageDto;
import com.demo.coffeePosMachine.exception.BusinessException;
import com.demo.coffeePosMachine.point.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.demo.coffeePosMachine.exception.ErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PointService pointService;
    private final UserRepository userRepository;
    private final LockedUserRepository lockedUserRepository;

    @Transactional(readOnly = true)
    public UserDto getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(USER_NOT_FOUND))
                .toDto();
    }

    @Override
    public void payForOrder(Long userId, BeverageDto beverage) {
        // 1. 유저 포인트 업데이트
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(USER_NOT_FOUND));
        user.usePoint(beverage.getPrice());
        // 2. 포인트 반영 및 로그 남기기 - 이 위치여야 코드의 응집도가 높아짐
        pointService.savePaymentLog(userId, beverage);

    }

    @Override
    public void payForOrderWithOptimisticLock(Long userId, Long beveragePrice) {
        LockedUser user = lockedUserRepository.findByIdWithOptimisticLock(userId)
                .orElseThrow(() -> new BusinessException(USER_NOT_FOUND));
        user.usePoint(beveragePrice);
    }

}
