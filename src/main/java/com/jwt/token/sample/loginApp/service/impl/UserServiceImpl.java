package com.jwt.token.sample.loginApp.service.impl;

import com.jwt.token.sample.loginApp.domain.entity.Role;
import com.jwt.token.sample.loginApp.domain.entity.User;
import com.jwt.token.sample.loginApp.repository.RoleRepository;
import com.jwt.token.sample.loginApp.repository.UserRepository;
import com.jwt.token.sample.loginApp.service.NotificationService;
import com.jwt.token.sample.loginApp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final NotificationService notificationService;

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
    public List<User> getUsers(Pageable pageable) {
        log.info("fetching all users");
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.getContent();
    }

    @Override
    public Boolean sendMessageToUser() {
        Map<String, String> message = new HashMap<>();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            message.put(user.getUsername(), user.getPassword());
        }
        return notificationService.sendUserNotification(message);
    }
}
