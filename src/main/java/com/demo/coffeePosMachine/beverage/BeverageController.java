package com.demo.coffeePosMachine.beverage;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/beverage")
public class BeverageController {
    private final BeverageService beverageService;

    @GetMapping("/all")
    public ResponseEntity<?> showAllBeverages() {
        return ResponseEntity.ok().body(beverageService.showAllBeverages());
    }

    @GetMapping("/favorite")
    public ResponseEntity<?> showFavoriteBevarages() {
        return ResponseEntity.ok().body(beverageService.showFavoriteBeverages());
    }
}
