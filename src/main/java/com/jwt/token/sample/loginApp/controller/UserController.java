package com.jwt.token.sample.loginApp.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jwt.token.sample.loginApp.Service.UserService;
import com.jwt.token.sample.loginApp.config.WithFeatureToggle;
import com.jwt.token.sample.loginApp.constant.FeatureCode;
import com.jwt.token.sample.loginApp.domain.FeatureToggle;
import com.jwt.token.sample.loginApp.domain.User;
import lombok.AllArgsConstructor;
import lombok.With;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

    public final UserService userService;

    @WithFeatureToggle({FeatureCode.USER_TOGGLE})
    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }
}
