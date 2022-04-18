package com.jwt.token.sample.loginApp.service.impl;

import com.jwt.token.sample.loginApp.domain.entity.User;
import com.jwt.token.sample.loginApp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_SIZE = 20;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAllUsers_whenGetUserIsExecuted() {
        Pageable pageable = PageRequest.of(DEFAULT_PAGE,DEFAULT_SIZE);
        Page<User> listOfUsers = getUserPage();

        when(userRepository.findAll(any(Pageable.class)))
                .thenReturn(listOfUsers);

        List<User> response = userService.getUsers(pageable);

        assertEquals(listOfUsers.getContent().size(), response.size());
        assertEquals(listOfUsers.getContent().get(0).getPassword(), response.get(0).getPassword());
        assertEquals(listOfUsers.getContent().get(0).getId(), response.get(0).getId());
        assertEquals(listOfUsers.getContent().get(0).getUsername(), response.get(0).getUsername());
    }

    // Sample Assert Throws
//    @Test
//    void shouldThrowAnError_whenGetUserIsExecuted() {
//        Pageable pageable = PageRequest.of(DEFAULT_PAGE,DEFAULT_SIZE);
//        Page<User> listOfUsers = getUserPage();
//
//        when(userRepository.findAll(any(Pageable.class)))
//                .thenReturn(listOfUsers);
//
//        assertThrows(RuntimeException.class, () -> userService.getUsers(pageable));
//    }

    private Page<User> getUserPage(){
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setUsername("Hello");
        user.setPassword("World");
        user.setId(1L);
        users.add(user);
        return new PageImpl<>(users);
    }
}