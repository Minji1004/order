package com.practice.store.auth.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class VerifyCodeCache {

    private static final int MAX_AGE_DAY = 1;
    private final String KEY_PREFIX = "verifyCode:";
    private final ObjectMapper objectMapper;
    private final StringRedisTemplate redisTemplate;

    private String getVerifyCodeKey(String verifyCode) {
        return KEY_PREFIX + verifyCode;
    }

    public String get(String verifyCode) throws JsonProcessingException {
        ValueOperations<String, String> ops = this.redisTemplate.opsForValue();

        String key = getVerifyCodeKey(verifyCode);

        if (!this.redisTemplate.hasKey(key)) {
            return null;
        } else {
            return ops.get(key);
        }
    }

    public void set(String verifyCode, String email) throws Exception {
        ValueOperations<String, String> ops = this.redisTemplate.opsForValue();
        String key = getVerifyCodeKey(verifyCode);
        String value = email;

        ops.set(key, value, MAX_AGE_DAY, TimeUnit.DAYS);
    }

    public void remove(String verifyCode) {
        redisTemplate.delete(getVerifyCodeKey(verifyCode));
    }

}
