package com.practice.store.order.service.internal;

import com.practice.store.order.entity.OrderEntity;
import com.practice.store.order.model.response.OrderResponse;
import com.practice.store.order.repository.OrderQRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class InternalOrderService {

    private final OrderQRepository orderQRepository;

    /*
    유저 별 마지막 주문 정보 조회
     */
    public Map<Long, OrderResponse> getLastOrderMap(List<Long> userIdList) {
        List<OrderEntity> lastOrderList = orderQRepository.getLastOrderList(userIdList);
        Map<Long, List<OrderEntity>> orderEntityMap = lastOrderList
                .stream()
                .collect(Collectors.groupingBy(order -> order.getUser().getUserId()));

        Map<Long, OrderResponse> resultMap = new HashMap<>();
        orderEntityMap.forEach((key, value)-> {
            resultMap.put(key, new OrderResponse(value.get(0)));
        });

        return resultMap;
    }
}
