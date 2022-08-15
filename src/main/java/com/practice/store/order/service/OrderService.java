package com.practice.store.order.service;

import com.practice.store.order.entity.OrderEntity;
import com.practice.store.order.model.request.OrderRequest;
import com.practice.store.order.repository.OrderIRepository;
import com.practice.store.user.entity.UserEntity;
import com.practice.store.user.model.User;
import com.practice.store.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final UserService userService;
    private final OrderIRepository orderIRepository;

    /*
    주문하기
     */
    public void order(String email, Long userId, OrderRequest orderRequest) {

        UserEntity userEntity = userService.getUserEntity(email, userId);
        String orderNo = ""; //랜덤 문자열
        OrderEntity orderEntity = new OrderEntity(orderNo, orderRequest.getItemName(), LocalDateTime.now(), userEntity);

        orderIRepository.save(orderEntity);
    }
}
