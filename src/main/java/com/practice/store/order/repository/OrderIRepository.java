package com.practice.store.order.repository;

import com.practice.store.order.entity.OrderEntity;
import com.practice.store.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderIRepository extends JpaRepository<OrderEntity, String> {
    List<OrderEntity> findAllByUser(UserEntity userEntity);
}
