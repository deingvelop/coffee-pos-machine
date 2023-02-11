package com.demo.coffeePosMachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class CoffeePosMachineApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoffeePosMachineApplication.class, args);
    }
}
