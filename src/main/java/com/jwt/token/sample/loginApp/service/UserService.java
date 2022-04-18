package com.jwt.token.sample.loginApp.service;

import com.jwt.token.sample.loginApp.domain.entity.Role;
import com.jwt.token.sample.loginApp.domain.entity.User;
import com.jwt.token.sample.loginApp.dto.UserCreationForm;
import com.jwt.token.sample.loginApp.dto.UserCreationResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    UserCreationResponse saveUser(UserCreationForm user);

    Role saveRole(Role role);

    User getUser(String username);

    List<User> getUsers(Pageable pageable);

    Boolean sendMessageToUser();
}
