package com.practice.store.user.model.response;

import com.practice.store.user.model.User;
import com.practice.store.user.type.Sex;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class UserDetailResponse {
    @ApiModelProperty(value = "유저 ID", example = "1")
    private Long userId;
    @ApiModelProperty(value = "이름", example = "홍길동")
    private String name;
    @ApiModelProperty(value = "별명" , example = "길동이")
    private String nickname;
    @ApiModelProperty(value = "이메일", example = "abc@def.com")
    private String email;
    @ApiModelProperty(value = "전화번호", example = "01011113333")
    private String mobile;
    @ApiModelProperty(value = "성별", example = "MALE")
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
