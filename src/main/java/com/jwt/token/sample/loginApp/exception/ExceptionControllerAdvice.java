package com.jwt.token.sample.loginApp.exception;

import com.jwt.token.sample.loginApp.exception.response.ErrorResource;
import com.jwt.token.sample.loginApp.exception.response.ErrorResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = ApiException.class)
    public ErrorResponseEntity apiException(ApiException e){
        log.error(e.getMessage(), e);
        if(e.getHttpStatus() == null){
            return extractValues(e);
        }

        return ErrorResponseEntity.globalError(
                e.getHttpStatus(), e.getValue(), e.getDescription(), e.getCode(), e.getHeading()
        );
    }

    private ErrorResponseEntity<ErrorResource> extractValues(ApiException e){
        System.out.println("Mermer" + e.getLocalizedMessage());
        String httpStatusStr = StringUtils.substringBetween(
                e.getLocalizedMessage(), "httpStatus=", ", value=")
                .replaceAll("\\d", "").trim();
        HttpStatus httpStatus = HttpStatus.valueOf(httpStatusStr);
        String message = StringUtils.substringBetween(
                e.getLocalizedMessage(), "value=", ", description=");
        return ErrorResponseEntity.globalError(httpStatus, message);
    }
}
