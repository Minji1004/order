package com.practice.store.order.model.response;

import com.practice.store.order.entity.OrderEntity;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OrderResponse {

    @ApiModelProperty(value = "주문 번호")
    private String orderNo;
    @ApiModelProperty(value = "아이템명")
    private String itemName;
    @ApiModelProperty(value = "주문 일자")
    private LocalDateTime orderDateTime;

    @QueryProjection
    public OrderResponse(OrderEntity orderEntity){
        this.orderNo = orderEntity.getOrderNo();
        this.itemName = orderEntity.getItemName();
        this.orderDateTime = orderEntity.getOrderDateTime();
    }
}
