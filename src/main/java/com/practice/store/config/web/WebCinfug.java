package com.practice.store.config.web;

import com.practice.store.auth.interceptor.LoginCheckerInterceptor;
import com.practice.store.auth.resolver.AccessTokenResolverConfig;
import com.practice.store.auth.service.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebCinfug implements WebMvcConfigurer {

    @Autowired
    private TokenProvider tokenProvider;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckerInterceptor());
    }

    @Bean
    public LoginCheckerInterceptor loginCheckerInterceptor() {
        return new LoginCheckerInterceptor();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*")
                .allowedOriginPatterns("*")
                .allowCredentials(true);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(getAccessTokenResolverConfig());
    }

    @Bean
    public AccessTokenResolverConfig getAccessTokenResolverConfig() {
        return new AccessTokenResolverConfig(tokenProvider);
    }

}
