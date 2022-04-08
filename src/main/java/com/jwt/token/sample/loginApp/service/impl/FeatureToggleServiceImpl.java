package com.jwt.token.sample.loginApp.service.impl;

import com.jwt.token.sample.loginApp.service.FeatureToggleService;
import com.jwt.token.sample.loginApp.constant.FeatureCode;
import com.jwt.token.sample.loginApp.domain.FeatureToggle;
import com.jwt.token.sample.loginApp.repository.FeatureToggleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeatureToggleServiceImpl implements FeatureToggleService {

    private final FeatureToggleRepository featureToggleRepository;
    private static final String DEFAULT_ERROR_MESSAGE = "This feature is currently unavailable";

    @Override
    public boolean checkFeatureEnabled(FeatureCode featureCode) {
        return featureToggleRepository.findByFeatureCode(featureCode)
                .map(FeatureToggle::getEnabled)
                .orElse(true);
    }

    @Override
    public String getCustomErrorMessage(FeatureCode featureCode) {
        return featureToggleRepository.findByFeatureCode(featureCode)
                .map(FeatureToggle::getCustomErrorMessage)
                .orElse(DEFAULT_ERROR_MESSAGE);
    }


}
