package com.jwt.token.sample.loginApp.Service.impl;

import com.jwt.token.sample.loginApp.Service.UserService;
import com.jwt.token.sample.loginApp.domain.Role;
import com.jwt.token.sample.loginApp.domain.User;
import com.jwt.token.sample.loginApp.repository.RoleRepository;
import com.jwt.token.sample.loginApp.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User saveUser(User user) {
        log.info("new user has been save : {}", user.getUsername());
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("new role has been save : {}", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public User getUser(String username) {
        log.info("fetching user {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("fetching all users");
        return userRepository.findAll();
    }
}
