package com.demo.coffeePosMachine.service;

import com.demo.coffeePosMachine.dto.request.PointRequestDto;
import com.demo.coffeePosMachine.dto.response.PointResponseDto;
import com.demo.coffeePosMachine.entity.PointLog;
import com.demo.coffeePosMachine.entity.User;
import com.demo.coffeePosMachine.exception.BusinessException;
import com.demo.coffeePosMachine.repository.PointLogRepository;
import com.demo.coffeePosMachine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.demo.coffeePosMachine.exception.ErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService{
    private final UserRepository userRepository;
    private final PointLogRepository pointLogRepository;

    @Override
    @Transactional
    public PointResponseDto updatePoint(PointRequestDto requestDto) {
        Long userId = requestDto.getUserId();
        Long chargingPoint = requestDto.getPoint();

        // 1. USER 테이블 - 포인트 업데이트
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(USER_NOT_FOUND));

        user.changePoint(chargingPoint);
        userRepository.save(user);

        // 2. POINT_LOG 테이블 - 포인트 충전 내역 기록
        PointLog pointLog = PointLog.builder()
                .userId(userId)
                .point(chargingPoint)
                .build();
        pointLogRepository.save(pointLog);

        // 3. 변화된 포인트 상태를 Response로 반환
        return new PointResponseDto(user.getId(), user.getPoint());
    }

}
