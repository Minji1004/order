package com.practice.store.auth.service;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import com.practice.store.auth.model.Token;
import com.practice.store.config.exception.util.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenProvider {
	private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
	
	private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "bearer";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;            // 30분

    private final Key key;

    public TokenProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }
	
    public Token generateToken(String email, String verifyCode) {
    	Date accessTokenExpiresIn = getExpiresDt();

    	String accessToken =
				Jwts.builder()
                .claim("email", email)
				.claim("verifyCode", verifyCode)
				.setExpiration(getExpiresDt())
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

    	return Token.builder()
                .grantType(BEARER_TYPE)
                .accessToken(accessToken)
                .accessTokenExpiresIn(accessTokenExpiresIn.getTime())
                .build();
    }

	private Date getExpiresDt() {
		LocalDateTime datetime = LocalDateTime.now().plusMinutes(ACCESS_TOKEN_EXPIRE_TIME);
		return java.util.Date.from(datetime.toInstant(ZoneOffset.UTC));
	}
    
    public boolean validateToken(String token) {
    	try {
    		Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
    		return true;
    	}catch(io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			ExceptionUtil.isException401(true, "잘못된 JWT 서명입니다");
    	}catch(ExpiredJwtException e) {
			ExceptionUtil.isException401(true, "만료된 JWT 토큰입니다");
    	}catch(UnsupportedJwtException e) {
			ExceptionUtil.isException401(true, "지원되지 않는 JWT 토큰입니다");
    	}catch(IllegalArgumentException e) {
			ExceptionUtil.isException401(true, "JWT 토큰이 잘못되었습니다.");
    	}
    	return false;
    }
    
    public Claims parseClaims(String accessToken) {
    	try {
    		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
    	}catch(ExpiredJwtException e) {
    		return e.getClaims();
    	}
    }
}
