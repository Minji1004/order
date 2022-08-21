package com.practice.store.auth.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequest {
    @ApiModelProperty(value = "이메일", example = "abc@def.com")
    private String email;
    @ApiModelProperty(value = "비밀번호", example = "123!@#abcABC")
    private String password;
}
