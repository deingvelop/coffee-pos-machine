package com.demo.coffeePosMachine.beverage;

import com.demo.coffeePosMachine.beverage.Beverage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeverageRepository extends JpaRepository<Beverage, Long> {
}
