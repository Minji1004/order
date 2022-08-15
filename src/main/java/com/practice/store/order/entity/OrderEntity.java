package com.practice.store.order.entity;

import com.practice.store.order.model.request.OrderRequest;
import com.practice.store.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDER_TABLE")
public class OrderEntity {

    @Id
    @Column(name = "ORDER_NO")
    private String orderNo;

    @Column(name = "ITEM_NAME")
    private String itemName;

    @Column(name = "ORDER_DATE_TIME")
    private LocalDateTime orderDateTime;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity userEntity;
}