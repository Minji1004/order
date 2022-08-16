package com.practice.store.order.repository;

import com.practice.store.order.entity.OrderEntity;
import com.practice.store.order.entity.QOrderEntity;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.practice.store.order.entity.QOrderEntity.orderEntity;

@Repository
@RequiredArgsConstructor
public class OrderQRepository {

    private final JPAQueryFactory queryFactory;

    public List<OrderEntity> getLastOrderList(List<Long> userIdList){
        QOrderEntity subOrderEntity = new QOrderEntity("subOrderEntity");
        List<OrderEntity> lastOrderList = queryFactory
                .select(orderEntity)
                .from(orderEntity)
                .where(
                        orderEntity.user.userId.in(userIdList),
                        JPAExpressions
                                .select(subOrderEntity.orderDateTime.max())
                                .from(subOrderEntity)
                                .where(subOrderEntity.user.userId.eq(orderEntity.user.userId))
                                .eq(orderEntity.orderDateTime)
                )
                .fetch();

        return lastOrderList;
    }

}
