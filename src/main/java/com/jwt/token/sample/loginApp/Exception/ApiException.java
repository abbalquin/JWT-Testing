package com.jwt.token.sample.loginApp.Exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiException extends RuntimeException{

    private HttpStatus httpStatus;
    private String value;
    private String description;
    private String code;
    private String heading;

    public ApiException(String value, String description, String code, String heading){
        super(value);
        this.httpStatus = HttpStatus.FORBIDDEN;
        this.description = description;
        this.code = code;
        this.heading = heading;
    }

    public ApiException(HttpStatus httpStatus, String value, String description){
        super(value);
        this.httpStatus = httpStatus;
        this.value = value;
        this.description = description;
    }

    public ApiException(HttpStatus httpStatus, String value){
        this(httpStatus, value, null);
    }

}
