package com.demo.coffeePosMachine.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "order_log")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderLog {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @Column
    private Long beverageId;

    @CreationTimestamp // INSERT 시 자동으로 값을 채워줌
    @Column
    private LocalDateTime createdAt = LocalDateTime.now();
}
