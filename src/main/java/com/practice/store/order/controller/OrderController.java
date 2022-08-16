package com.practice.store.order.controller;

import com.practice.store.auth.annotation.ParamInAccessToken;
import com.practice.store.auth.type.AccessTokenParamType;
import com.practice.store.order.model.request.OrderRequest;
import com.practice.store.order.model.response.OrderResponse;
import com.practice.store.order.service.external.ExternalOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/order")
public class OrderController {

    private final ExternalOrderService orderService;

    /*
    단일 회원의 주문 목록 조회
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<OrderResponse>> getOrderList(
            @ParamInAccessToken(value = AccessTokenParamType.EMAIL) String email,
            @PathVariable("userId") Long userId
          ) throws Exception {
        return ResponseEntity.ok().body(orderService.getOrderList(email, userId));
    }

    /*
    주문하기
     */
    @PostMapping("/{userId}")
    public ResponseEntity<Void> orderItem(
            @ParamInAccessToken(value = AccessTokenParamType.EMAIL) String email,
            @PathVariable("userId") Long userId,
            @RequestBody OrderRequest orderRequest
    ) throws Exception {
        orderService.order(email,userId, orderRequest);
        return ResponseEntity.ok().build();
    }

}
