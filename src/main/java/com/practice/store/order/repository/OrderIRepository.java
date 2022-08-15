package com.practice.store.order.repository;

import com.practice.store.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderIRepository extends JpaRepository<OrderEntity, String> {
}
