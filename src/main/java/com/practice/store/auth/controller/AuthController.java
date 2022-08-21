package com.practice.store.auth.controller;

import com.practice.store.auth.annotation.CheckLogin;
import com.practice.store.auth.annotation.ParamInAccessToken;
import com.practice.store.auth.model.request.LoginRequest;
import com.practice.store.auth.model.response.LoginResponse;
import com.practice.store.auth.service.AuthService;
import com.practice.store.auth.type.AccessTokenParamType;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
@Api(tags = {"인증 API"})
public class AuthController {

    private final AuthService authService;

    /*
    로그인
     */
    @PostMapping(value="/login")
    @ApiOperation(value = "로그인")
    @ApiResponses({
            @ApiResponse(
                    code = 400
                    , message = "로그인 정보 유효성 검사 실패"
            ),
            @ApiResponse(
                    code = 401
                    , message = "인증 실패"
            )
    })
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) throws Exception {
        return ResponseEntity.ok().body(authService.login(loginRequest));
    }

    /*
    로그아웃
     */
    @CheckLogin
    @PostMapping(value="/logout")
    @ApiOperation(value = "로그아웃")
    public ResponseEntity<Void> logOut(@ApiIgnore @ParamInAccessToken(value = AccessTokenParamType.VERIFY_CODE) String verifyCode) throws Exception {
        authService.logout(verifyCode);
        return ResponseEntity.ok().build();
    }

}
