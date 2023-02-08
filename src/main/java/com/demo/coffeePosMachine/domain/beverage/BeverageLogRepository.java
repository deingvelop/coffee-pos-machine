package com.demo.coffeePosMachine.domain.beverage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BeverageLogRepository extends JpaRepository<BeverageLog, Long> {
}
