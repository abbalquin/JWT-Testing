package com.jwt.token.sample.loginApp.config;

import com.jwt.token.sample.loginApp.constant.FeatureCode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface WithFeatureToggle {

    FeatureCode[] value();

    boolean throwException() default true;

    boolean withCustomErrorMessage() default false;
}
