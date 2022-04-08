package com.jwt.token.sample.loginApp.config;

import com.jwt.token.sample.loginApp.Exception.FeatureDisabledException;
import com.jwt.token.sample.loginApp.Service.FeatureToggleService;
import com.jwt.token.sample.loginApp.config.WithFeatureToggle;
import com.jwt.token.sample.loginApp.constant.FeatureCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class WithFeatureToggleAspect {

    private final FeatureToggleService featureToggleService;

    @Around(value = "@annotation(featureToggle)"
            , argNames = "joinPoint,featureToggle")
    public Object validateFeatureToggle(ProceedingJoinPoint joinPoint,
                                        WithFeatureToggle featureToggle) throws Throwable {
        for (FeatureCode featureCode : featureToggle.value()) {
            boolean enabled = featureToggleService.checkFeatureEnabled(featureCode);

            if (!enabled) {
                if (featureToggle.withCustomErrorMessage()) {
                    throw new FeatureDisabledException(featureToggleService.getCustomErrorMessage(featureCode));
                } else {
                    throw new FeatureDisabledException(featureCode);
                }
            }
        }

        return joinPoint.proceed();
    }

}
