package com.practice.store.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.store.user.entity.UserEntity;

@Repository
public interface UserIRepository extends JpaRepository<UserEntity, Long>{
}
