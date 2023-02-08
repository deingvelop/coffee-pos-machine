package com.demo.coffeePosMachine.repository;

import com.demo.coffeePosMachine.entity.PointLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointLogRepository extends JpaRepository<PointLog, Long> {
}
