package com.practice.store.user.model;

import com.practice.store.order.model.response.OrderResponse;
import com.querydsl.core.util.BeanUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Order {
    private String orderNo;
    private String itemName;
    private LocalDateTime orderDateTime;

    public Order(OrderResponse orderResponse){
        this.orderNo = orderResponse.getOrderNo();
        this.itemName = orderResponse.getItemName();
        this.orderDateTime = orderResponse.getOrderDateTime();
    }
}
