package com.demo.coffeePosMachine.point;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "point_log")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PointLog {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @Column
    private Long point;

    @Column
    private PointLogType type;

    @CreationTimestamp // INSERT 시 자동으로 값을 채워줌
    @Column
    private LocalDateTime createdAt = LocalDateTime.now();

    public PointLog(Long userId, Long point, PointLogType type) {
        this.userId = userId;
        this.point = point;
        this.type = type;
    }
}
