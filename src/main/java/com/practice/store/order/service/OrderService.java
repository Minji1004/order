package com.practice.store.order.service;

import com.practice.store.order.entity.OrderEntity;
import com.practice.store.order.model.request.OrderRequest;
import com.practice.store.order.repository.OrderIRepository;
import com.practice.store.order.utils.StringUtil;
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
        // orderNo 중복 체크 해야함.
        String orderNo = generatedOrderNo();
        OrderEntity orderEntity = new OrderEntity(orderNo, orderRequest.getItemName(), LocalDateTime.now(), userEntity);

        orderIRepository.save(orderEntity);
    }

    private String generatedOrderNo(){
        String orderNo = StringUtil.genRandomString(12);
        while(isDuplicatedOrderNo(orderNo)){
            orderNo = StringUtil.genRandomString(12);
        }

        return orderNo;
    }

    private boolean isDuplicatedOrderNo(String orderNo){
        OrderEntity orderEntity = orderIRepository.findById(orderNo).orElse(null);
        return orderEntity != null;
    }
}
