package com.demo.coffeePosMachine.repository;

import com.demo.coffeePosMachine.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
