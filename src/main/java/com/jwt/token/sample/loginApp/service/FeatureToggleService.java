package com.jwt.token.sample.loginApp.service;

import com.jwt.token.sample.loginApp.constant.FeatureCode;

public interface FeatureToggleService {

    boolean checkFeatureEnabled(FeatureCode featureCode);

    String getCustomErrorMessage(FeatureCode featureCode);
}
