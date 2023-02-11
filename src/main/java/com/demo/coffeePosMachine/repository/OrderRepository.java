package com.demo.coffeePosMachine.repository;

import com.demo.coffeePosMachine.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}