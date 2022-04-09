package com.jwt.token.sample.loginApp.service;

import com.jwt.token.sample.loginApp.domain.entity.Role;
import com.jwt.token.sample.loginApp.domain.entity.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    Role saveRole(Role role);
    User getUser(String username);
    List<User> getUsers();
}
