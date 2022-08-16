package com.practice.store.auth.service;

import com.practice.store.config.exception.util.ExceptionUtil;
import com.practice.store.user.service.internal.InternalUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class AuthValidationService {

    private final InternalUserService internalUserService;

    public void checkLoginInfo(String email, String password){
        ExceptionUtil.isException400(!StringUtils.hasText(email), "이메일을 입력해주세요.");
        ExceptionUtil.isException400(!StringUtils.hasText(password), "비밀번호를 입력해주세요.");
        ExceptionUtil.isException401(!internalUserService.checkUser(email, password), "잘못된 로그인 정보입니다.");
    }
}
