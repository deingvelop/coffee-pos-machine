package com.demo.coffeePosMachine.user;

import com.demo.coffeePosMachine.exception.BusinessException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.demo.coffeePosMachine.exception.ErrorCode.OUT_OF_POINT;

@Entity(name = "locked_user")
@Getter
@NoArgsConstructor
public class LockedUser {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long point;

    @Version
    private Long version;
    // 동시성 제어에 대해 좀 더 개념 견고하게

    // Constructor
    public LockedUser(Long id, Long point) {
        this.id = id;
        this.point = point;
    }

    public void changePoint(Long chargingPoint) {
        this.point += chargingPoint;
    }

    public void usePoint(Long beveragePrice) {
        if (beveragePrice > point) {
            throw new BusinessException(OUT_OF_POINT);
        }
        this.point -= beveragePrice;
    }

    public UserDto toDto() {
        return new UserDto(id, point);
    }
}
