package com.practice.store.auth.util;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;

public class RequestDataUtil {

    public static String getAccessToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        return getAccessToken(authorization);
    }

    public static String getAccessToken(NativeWebRequest request){
        String authorization = request.getHeader("Authorization");
        return getAccessToken(authorization);
    }

    private static String getAccessToken(String authorization){
        String accessToken = "";
        if (StringUtils.hasText(authorization) && authorization.startsWith("Bearer ")) {
            accessToken = authorization.substring(7);
        }

        return accessToken;
    }

}
