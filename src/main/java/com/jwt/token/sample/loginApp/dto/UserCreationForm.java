package com.jwt.token.sample.loginApp.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserCreationForm {

    private String username;

    private String password;
}
