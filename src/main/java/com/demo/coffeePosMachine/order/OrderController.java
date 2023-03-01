package com.demo.coffeePosMachine.order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
@Tag(name = "주문", description = "주문 관련 api 입니다.")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @Operation(summary = "음료 주문하기", description = "포인트로 음료를 주문합니다.")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequestDto requestDto) {
        return ResponseEntity.ok().body(orderService.createOrder(requestDto));
    }
}
