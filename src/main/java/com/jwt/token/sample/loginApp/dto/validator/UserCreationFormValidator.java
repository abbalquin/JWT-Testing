package com.jwt.token.sample.loginApp.dto.validator;

import com.jwt.token.sample.loginApp.dto.UserCreationForm;
import com.jwt.token.sample.loginApp.stereotype.FormValidator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.thymeleaf.util.Validate;

@FormValidator
public class UserCreationFormValidator implements Validator {

    // Min 1 Uppercase, Lowercase, Special Character and a Number
    // Min of 8 Character and Max of 30 charater
    private static final String PASSWORD_REGEX = "/^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{8,30}$/";

    @Override
    public boolean supports(Class<?> clazz) {
        return UserCreationForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "username", "001", "username is required");
        ValidationUtils.rejectIfEmpty(errors, "password", "002", "password is required");
    }
}
