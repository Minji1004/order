package com.practice.store.order.service.external;

import com.practice.store.order.entity.OrderEntity;
import com.practice.store.order.model.request.OrderRequest;
import com.practice.store.order.model.response.OrderResponse;
import com.practice.store.order.repository.OrderIRepository;
import com.practice.store.order.utils.StringUtil;
import com.practice.store.user.entity.UserEntity;
import com.practice.store.user.service.internal.InternalUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExternalOrderService {

    private final InternalUserService internalUserService;
    private final OrderIRepository orderIRepository;

    /*
    주문하기
     */
    public void order(String email, Long userId, OrderRequest orderRequest) {

        UserEntity userEntity = internalUserService.getUserEntity(email, userId);
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

    /*
    주문 목록 조회
     */
    public List<OrderResponse> getOrderList(String email, Long userId) {
        UserEntity userEntity = internalUserService.getUserEntity(email, userId);
        List<OrderEntity> orderEntityList = orderIRepository.findAllByUser(userEntity);

        return orderEntityList.stream().map(OrderResponse::new).collect(Collectors.toList());
    }
}
