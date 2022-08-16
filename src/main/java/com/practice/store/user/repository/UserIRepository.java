package com.practice.store.user.repository;

import com.practice.store.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.store.user.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserIRepository extends JpaRepository<UserEntity, Long>{
    Optional<UserEntity> findByEmail(String email);
}
