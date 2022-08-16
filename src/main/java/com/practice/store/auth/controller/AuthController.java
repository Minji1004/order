package com.practice.store.auth.controller;

import com.practice.store.auth.annotation.CheckLogin;
import com.practice.store.auth.annotation.ParamInAccessToken;
import com.practice.store.auth.model.request.LoginRequest;
import com.practice.store.auth.model.response.LoginResponse;
import com.practice.store.auth.service.AuthService;
import com.practice.store.auth.type.AccessTokenParamType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    /*
    로그인
     */
    @PostMapping(value="/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) throws Exception {
        return ResponseEntity.ok().body(authService.login(loginRequest));
    }

    /*
    로그아웃
     */
    @CheckLogin
    @PostMapping(value="/logout")
    public ResponseEntity<Void> logOut(@ParamInAccessToken(value = AccessTokenParamType.VERIFY_CODE) String verifyCode) throws Exception {
        authService.logout(verifyCode);
        return ResponseEntity.ok().build();
    }

}
