package com.demo.coffeePosMachine.entity;

import com.demo.coffeePosMachine.dto.UserDto;
import com.demo.coffeePosMachine.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.demo.coffeePosMachine.exception.ErrorCode.OUT_OF_POINT;

@Entity(name = "`user`")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long point;

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
