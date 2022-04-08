package com.jwt.token.sample.loginApp.Exception.Response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

@Slf4j
public class ErrorResponseEntity<T> extends ResponseEntity<ApiResponse>{

    public ErrorResponseEntity(List<ErrorResource> errors) {
        this(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public ErrorResponseEntity(List<ErrorResource> errors, HttpStatus status) {
        super(new ApiResponse<>(status.value(), errors), status);
    }

    public static ErrorResponseEntity<ErrorResource> globalError(HttpStatus code, String value) {
        return globalError(code, value, null, null, null);
    }

    public static ErrorResponseEntity<ErrorResource> globalError(HttpStatus statusCode, String value, String description, String code, String heading){
        List<ErrorResource> errors = Collections.singletonList(new ErrorResource(value, description, code, heading));
        log.error(">>>>>>>>>> error : " + value + " ... " + description);
        return new ErrorResponseEntity<>(errors, statusCode);
    }
}
