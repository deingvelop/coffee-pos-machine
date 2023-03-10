package com.demo.coffeePosMachine.point;

import com.demo.coffeePosMachine.beverage.BeverageDto;
import com.demo.coffeePosMachine.exception.BusinessException;
import com.demo.coffeePosMachine.user.User;
import com.demo.coffeePosMachine.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.demo.coffeePosMachine.exception.ErrorCode.INVALID_POINT_VALUE;
import static com.demo.coffeePosMachine.exception.ErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {
    private final UserRepository userRepository;
    private final PointLogRepository pointLogRepository;

    @Override
    @Transactional
    public PointResponseDto updatePoint(PointRequestDto requestDto) {
        Long userId = requestDto.getUserId();
        Long chargingPoint = requestDto.getPoint();

        // 0. 예외 처리 - 충전 금액이 양의 정수가 아니라면 오류 보내기
        if (chargingPoint <= 0) {
            throw new BusinessException(INVALID_POINT_VALUE);
        }

        // 1. USER 테이블 - 포인트 업데이트
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(USER_NOT_FOUND));

        user.changePoint(chargingPoint);
        userRepository.save(user);

        // 2. POINT_LOG 테이블 - 포인트 충전 내역 기록
        PointLog pointLog = new PointLog(userId, chargingPoint, PointLogType.CHARGE);
        pointLogRepository.save(pointLog);

        // 3. 변화된 포인트 상태를 Response로 반환
        return new PointResponseDto(user.getId(), user.getPoint());
    }

    @Override
    public void savePaymentLog(Long userId, BeverageDto beverageDto) {
        PointLog pointLog = new PointLog (userId, beverageDto.getPrice(), PointLogType.PAY);
        pointLogRepository.save(pointLog);
    }

}
