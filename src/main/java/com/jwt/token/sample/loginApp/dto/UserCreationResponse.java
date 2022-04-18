package com.jwt.token.sample.loginApp.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserCreationResponse {

    private Long id;

    private String username;

    private String password;

    public String getPassword(){
        int passwordSize = this.password.length();
        String codedPassword = "";
        for(int i=0; i<passwordSize; i++) codedPassword += "*";
        return codedPassword;
    }
}
