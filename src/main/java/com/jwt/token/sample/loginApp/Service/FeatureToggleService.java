package com.jwt.token.sample.loginApp.Service;

import com.jwt.token.sample.loginApp.constant.FeatureCode;

public interface FeatureToggleService {

    boolean checkFeatureEnabled(FeatureCode featureCode);

    String getCustomErrorMessage(FeatureCode featureCode);
}
