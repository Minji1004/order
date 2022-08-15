package com.practice.store.auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class Token {
    private String grantType;
    private String accessToken;
    private Long accessTokenExpiresIn;
}
