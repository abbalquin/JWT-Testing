package com.jwt.token.sample.loginApp.Exception;

import com.jwt.token.sample.loginApp.constant.FeatureCode;
import org.springframework.http.HttpStatus;

public class FeatureDisabledException extends ApiException{

    private final static String MESSAGE_FORMAT = "%s is currently unavailable at the moment.";

    public FeatureDisabledException(String errorDescription){
        super(HttpStatus.FORBIDDEN, errorDescription);
    }

    public FeatureDisabledException(FeatureCode featureCode){
        super(HttpStatus.FORBIDDEN, String.format(MESSAGE_FORMAT, featureCode.getDescription()));
    }
}
