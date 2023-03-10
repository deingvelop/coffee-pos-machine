package com.demo.coffeePosMachine.aop.logging;

import com.demo.coffeePosMachine.order.OrderResponseDto;

public class OrderLogDto {
    private String orderId;
    private String userId;
    private String beverageName;
    private String beveragePrice;
    private String createdAt;

    public OrderLogDto(OrderResponseDto resDto) {
        this.orderId = String.valueOf(resDto.getOrderId());
        this.userId = String.valueOf(resDto.getUserId());
        this.beverageName = resDto.getBeverageName();
        this.beveragePrice = String.valueOf(resDto.getBeveragePrice());
        this.createdAt = String.valueOf(resDto.getCreatedAt());
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", beverageName='" + beverageName + '\'' +
                ", beveragePrice=" + beveragePrice +
                ", createdAt=" + createdAt +
                '}';
    }
}
