package com.practice.store.auth.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
    @ApiModelProperty(value = "토큰")
    private String accessToken;
}
