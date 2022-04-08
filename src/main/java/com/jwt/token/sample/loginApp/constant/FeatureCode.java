package com.jwt.token.sample.loginApp.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FeatureCode {

    USER_TOGGLE("User toggle"),
    ROLE_TOGGLE("Role toggle");

    private final String description;

    public static FeatureCode ofCode(String code){
        for(FeatureCode featureCode : values()){
            if(code.equals(featureCode.name()))
                return featureCode;
        }

        return null;
    }
}
