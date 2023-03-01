package com.demo.coffeePosMachine.beverage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "음료", description = "음료 관련 api 입니다.")
@RequestMapping("/beverage")
public class BeverageController {
    private final BeverageService beverageService;

    @GetMapping("/all")
    @Operation(summary = "모든 음료 조회하기", description = "모든 음료 목록을 출력합니다.", tags = {"View"})
    public ResponseEntity<List<BeverageDto>> showAllBeverages() {
        return ResponseEntity.ok().body(beverageService.showAllBeverages());
    }

    @GetMapping("/favorite")
    @Operation(summary = "인기 메뉴 조회하기", description = "최근 7일간 인기있는 메뉴 3개를 조회합니다.")
    public ResponseEntity<?> showFavoriteBevarages() {
        return ResponseEntity.ok().body(beverageService.showFavoriteBeverages());
    }
}
