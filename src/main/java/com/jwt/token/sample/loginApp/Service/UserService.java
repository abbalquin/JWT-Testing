package com.jwt.token.sample.loginApp.Service;

import com.jwt.token.sample.loginApp.domain.Role;
import com.jwt.token.sample.loginApp.domain.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    Role saveRole(Role role);
    User getUser(String username);
    List<User> getUsers();
}
