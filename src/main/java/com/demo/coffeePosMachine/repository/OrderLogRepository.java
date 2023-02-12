package com.demo.coffeePosMachine.repository;

import com.demo.coffeePosMachine.entity.OrderLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLogRepository extends JpaRepository<OrderLog, Long> {

}