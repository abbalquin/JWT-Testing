package com.jwt.token.sample.loginApp.Exception.Response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
public class ErrorResource {

    private String message;
    private String description;
    private String code;
    private String heading;
    private Map<String, String> details;
    private List<String> mismatchedIdDetails;

    public ErrorResource(String message, String description){
        this.message = message;
        this.description = description;
    }

    public ErrorResource(String message, String description, String code, String heading){
        this.message = message;
        this.description = description;
        this.code = code;
        this.heading = heading;
    }

    public ErrorResource(String message, String description, String code, String heading, Map<String, String> details){
        this.message = message;
        this.description = description;
        this.code = code;
        this.heading = heading;
        this.details = details;
    }

    public ErrorResource(String message, String description, String code, String heading, List<String> mismatchedIdDetails){
        this.message = message;
        this.description = description;
        this.code = code;
        this.heading = heading;
        this.mismatchedIdDetails = mismatchedIdDetails;
    }
}
