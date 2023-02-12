package com.demo.coffeePosMachine.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "order")
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

    @Column
    private LocalDateTime createdAt;
}
