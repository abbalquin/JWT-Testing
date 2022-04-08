package com.jwt.token.sample.loginApp.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FileContentType {

    EXCEL(".xlsx","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
    PDF(".pdf",""),
    CSV(".csv", ""),
    ;

    private final String extension;
    private final String contentType;
}
