package com.practice.store.auth.service;

import com.practice.store.auth.model.Token;
import com.practice.store.auth.model.request.LoginRequest;
import com.practice.store.auth.model.response.LoginResponse;
import com.practice.store.auth.repository.VerifyCodeCache;
import com.practice.store.config.exception.util.ExceptionUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthValidationService authValidationService;;
    private final TokenProvider tokenProvider;
    private final VerifyCodeCache verifyCodeCache;

    /*
    로그인
     */
    public LoginResponse login(LoginRequest loginRequest) throws Exception {
        authValidationService.checkLoginInfo(loginRequest.getEmail(), loginRequest.getPassword());

        String verifyCode = generateHash(loginRequest.getEmail());
        verifyCodeCache.set(verifyCode, loginRequest.getEmail());

        Token token = tokenProvider.generateToken(loginRequest.getEmail(), verifyCode);
        return new LoginResponse(token.getAccessToken());
    }

    private String generateHash(String value) throws NoSuchAlgorithmException {
        String msg = value + LocalDateTime.now();
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(msg.getBytes());

        return bytesToHex(md.digest());
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    /*
    로그아웃
     */
    public void logout(String verifyCode) {
        verifyCodeCache.remove(verifyCode);
    }

    /*
    로그인 체크할 때 사용.
     */
    public void checkLogin(String accessToken, HttpServletResponse response){
        tokenProvider.validateToken(accessToken);

        // access token 의 verifyCode가 redis 에 존재하는지 확인
        Claims claims = tokenProvider.parseClaims(accessToken);
        String verifyCode = (String)claims.get("verifyCode");

        try{
            if(verifyCodeCache.get(verifyCode) == null){
                ExceptionUtil.isException401(true, "다시 로그인 해주세요.");
            }

        }catch(Exception e){
            ExceptionUtil.isException401(true, "다시 로그인 해주세요.");
        }
    }
}
