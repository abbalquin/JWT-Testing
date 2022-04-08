package com.jwt.token.sample.loginApp.exception.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class ApiResponse<T> {

    private int code;
    private T data;
    private List<ErrorResource> errors;

    public ApiResponse(int code, T data) {
        this(code, data, null);
    }

    public ApiResponse(int code, T data, List<ErrorResource> errors){
        this.code = code;
        this.data = data;
        this.errors = errors;
    }

    public static <T> ApiResponse<T> ok(T data){
        return new ApiResponse<>(HttpStatus.OK.value(), data);
    }
}
