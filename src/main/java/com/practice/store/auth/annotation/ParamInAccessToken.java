package com.practice.store.auth.annotation;

import com.practice.store.auth.type.AccessTokenParamType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ParamInAccessToken {
    AccessTokenParamType value() default AccessTokenParamType.EMAIL;
}
