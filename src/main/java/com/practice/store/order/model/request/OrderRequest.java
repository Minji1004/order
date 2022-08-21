package com.practice.store.order.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderRequest {
    @ApiModelProperty(value = "아이템명", example = "스티커13")
    private String itemName;
}


