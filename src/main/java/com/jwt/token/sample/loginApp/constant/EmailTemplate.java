package com.jwt.token.sample.loginApp.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmailTemplate {

    SAMPLE_TEMPLATE("SAMPLE SUBJECT", "sample-email");

    private final String subject;
    private final String templatePath;
}
