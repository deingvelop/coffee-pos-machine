package com.demo.coffeePosMachine.entity;

import com.demo.coffeePosMachine.dto.BeverageDto;
import com.demo.coffeePosMachine.dto.request.OrderRequestDto;
import com.demo.coffeePosMachine.dto.response.OrderResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "`order`")
@Getter
//@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @Column
    private Long beverageId;

    @Column
    private String beverageName;

    @Column
    private Long beveragePrice;

    @CreationTimestamp // INSERT 시 자동으로 값을 채워줌
    @Column
    private LocalDateTime createdAt = LocalDateTime.now();

    public Order(OrderRequestDto requestDto, BeverageDto beverage) {
        this.userId = requestDto.getUserId();
        this.beverageId = beverage.getBeverageId();
        this.beverageName = beverage.getName();
        this.beveragePrice = beverage.getPrice();
    }

    public OrderResponseDto toResponseDto() {
        return new OrderResponseDto(id, userId, beverageId, beverageName, beveragePrice, createdAt);
    }
}
