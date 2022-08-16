package com.practice.store.user.model.response;

import com.practice.store.user.model.User;
import com.practice.store.user.type.Sex;
import lombok.Getter;

@Getter
public class UserDetailResponse {
    private Long userId;
    private String name;
    private String nickname;
    private String email;
    private String mobile;
    private Sex sex;

    public UserDetailResponse(User user){
        this.userId = user.getUserId();
        this.name = user.getName();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.mobile = user.getMobile();
        this.sex = user.getSex();
    }
}
