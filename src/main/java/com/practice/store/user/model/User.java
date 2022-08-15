package com.practice.store.user.model;

import com.practice.store.user.entity.UserEntity;
import com.practice.store.user.type.Sex;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class User {
    private Long userId;
    private String name;
    private String nickname;
    private String password;
    private String email;
    private String mobile;
    private Sex sex;

    @QueryProjection
    public User(UserEntity userEntity){
        this.userId = userEntity.getUserId();
        this.name = userEntity.getName();
        this.nickname = userEntity.getNickname();
        this.password = userEntity.getPassword();
        this.email = userEntity.getEmail();
        this.mobile = userEntity.getMobile();
        this.sex = userEntity.getSex();
    }
}
