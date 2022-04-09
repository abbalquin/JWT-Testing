package com.jwt.token.sample.loginApp.controller;

import com.jwt.token.sample.loginApp.service.UserService;
import com.jwt.token.sample.loginApp.config.WithFeatureToggle;
import com.jwt.token.sample.loginApp.constant.FeatureCode;
import com.jwt.token.sample.loginApp.domain.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
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
    public List<User> getUsers(Pageable pageable){
        return userService.getUsers(pageable);
    }

}
