package com.demo.coffeePosMachine.controller;

import com.demo.coffeePosMachine.service.BeverageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/beverage")
public class BeverageController {
    private final BeverageService beverageService;

}
