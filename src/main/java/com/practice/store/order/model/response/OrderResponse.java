package com.practice.store.order.model.response;

import com.practice.store.order.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OrderResponse {
    private String orderNo;
    private String itemName;
    private LocalDateTime orderDateTime;

    public OrderResponse(OrderEntity orderEntity){
        this.orderNo = orderEntity.getOrderNo();
        this.itemName = orderEntity.getItemName();
        this.orderDateTime = orderEntity.getOrderDateTime();
    }
}