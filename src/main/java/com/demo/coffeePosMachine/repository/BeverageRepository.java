package com.demo.coffeePosMachine.repository;

import com.demo.coffeePosMachine.entity.Beverage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeverageRepository extends JpaRepository<Beverage, Long> {
}
