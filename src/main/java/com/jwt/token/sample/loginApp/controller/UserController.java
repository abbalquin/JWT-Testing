package com.jwt.token.sample.loginApp.controller;

import com.jwt.token.sample.loginApp.config.WithFeatureToggle;
import com.jwt.token.sample.loginApp.constant.FeatureCode;
import com.jwt.token.sample.loginApp.domain.entity.User;
import com.jwt.token.sample.loginApp.dto.UserCreationForm;
import com.jwt.token.sample.loginApp.dto.UserCreationResponse;
import com.jwt.token.sample.loginApp.dto.validator.UserCreationFormValidator;
import com.jwt.token.sample.loginApp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

    @InitBinder("userCreationForm")
    protected void initUserCreationForm(WebDataBinder webDataBinder){
        webDataBinder.addValidators(new UserCreationFormValidator());
    }

    public final UserService userService;

    @WithFeatureToggle({FeatureCode.USER_TOGGLE})
    @GetMapping("/users")
    public List<User> getUsers(Pageable pageable) {
        return userService.getUsers(pageable);
    }

    @PostMapping("/users/send")
    public boolean sendMessageToUsers() {
        return userService.sendMessageToUser();
    }

    @PostMapping("/user")
    public UserCreationResponse saveUser(@Valid @RequestBody UserCreationForm form){
        return userService.saveUser(form);
    }

}
