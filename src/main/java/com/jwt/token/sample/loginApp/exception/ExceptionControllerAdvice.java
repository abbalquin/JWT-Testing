package com.jwt.token.sample.loginApp.exception;

import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;
import com.jwt.token.sample.loginApp.exception.response.ErrorResource;
import com.jwt.token.sample.loginApp.exception.response.ErrorResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

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

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ErrorResponseEntity apiException(MethodArgumentNotValidException e){
        log.error(e.getMessage(), e);

        String message = "Please provide complete and valid form values.";
        List<ObjectError> errors = e.getBindingResult().getAllErrors();

        if (!errors.isEmpty()) {

            List<ErrorResource> errorResources = errors
                    .stream()
                    .map(objectError -> {
                        ErrorResource errorResource;
                        String descErrorMessage;

                        if (objectError instanceof FieldError) {
                            FieldError fieldError = (FieldError) objectError;
                            descErrorMessage = camelToSnakeCase().convert(fieldError.getField()) + " "
                                    + fieldError.getDefaultMessage();
                            errorResource = new ErrorResource(fieldError.getDefaultMessage(), descErrorMessage);
                            errorResource.setCode(fieldError.getCode());
                        } else {
                            errorResource =
                                    new ErrorResource(objectError.getDefaultMessage(), objectError.getDefaultMessage());
                        }

                        return errorResource;
                    }).collect(Collectors.toList());

            return ErrorResponseEntity.globalError(HttpStatus.UNPROCESSABLE_ENTITY, errorResources);
        }

        return ErrorResponseEntity.globalError(message);
    }

    private ErrorResponseEntity<ErrorResource> extractValues(ApiException e){
        String httpStatusStr = StringUtils.substringBetween(
                e.getLocalizedMessage(), "httpStatus=", ", value=")
                .replaceAll("\\d", "").trim();
        HttpStatus httpStatus = HttpStatus.valueOf(httpStatusStr);
        String message = StringUtils.substringBetween(
                e.getLocalizedMessage(), "value=", ", description=");
        return ErrorResponseEntity.globalError(httpStatus, message);
    }

    private Converter<String, String> camelToSnakeCase() {
        return CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE);
    }
}
