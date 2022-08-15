package com.practice.store.auth.resolver;

import com.practice.store.auth.annotation.ParamInAccessToken;
import com.practice.store.auth.service.TokenProvider;
import com.practice.store.auth.type.AccessTokenParamType;
import com.practice.store.auth.util.RequestDataUtil;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@AllArgsConstructor
public class AccessTokenResolverConfig implements HandlerMethodArgumentResolver {

    private final TokenProvider tokenProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ParamInAccessToken.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        ParamInAccessToken annotationParam = parameter.getParameterAnnotation(ParamInAccessToken.class);
        AccessTokenParamType paramType = annotationParam.value();

        String accessToken = RequestDataUtil.getAccessToken(webRequest);
        Claims claims = tokenProvider.parseClaims(accessToken);

        if (paramType.equals(AccessTokenParamType.EMAIL)) {
            return (String)claims.get("email");
        }else{
            return (String)claims.get("verifyCode");
        }
    }
}
