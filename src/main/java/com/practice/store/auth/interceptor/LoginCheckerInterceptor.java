package com.practice.store.auth.interceptor;

import com.practice.store.auth.annotation.CheckLogin;
import com.practice.store.auth.service.AuthService;
import com.practice.store.auth.util.RequestDataUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCheckerInterceptor implements HandlerInterceptor {

    @Autowired
    private AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;

            if (!hm.hasMethodAnnotation(CheckLogin.class)) {
                return true;
            }

            String accessToken = RequestDataUtil.getAccessToken(request);
            authService.checkLogin(accessToken, response);
        }

        return true;
    }

}
