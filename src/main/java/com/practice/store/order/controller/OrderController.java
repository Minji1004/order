package com.practice.store.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

	// 단일 회원의 주문 목록 조회
    @GetMapping("/{userId}")
    public ResponseEntity<String> orderList() throws Exception {
        // user 인증
        return ResponseEntity.ok("test");
    }

    // 주문하기
    @PostMapping("/{userId}")
    public ResponseEntity<String> orderItem() throws Exception {
        // user 인증
        return ResponseEntity.ok("test");
    }

}
