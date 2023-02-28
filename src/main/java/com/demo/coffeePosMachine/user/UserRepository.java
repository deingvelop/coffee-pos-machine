package com.demo.coffeePosMachine.user;

import com.demo.coffeePosMachine.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
