package com.jwt.token.sample.loginApp.mapper;

import com.jwt.token.sample.loginApp.domain.entity.User;
import com.jwt.token.sample.loginApp.dto.UserCreationForm;
import com.jwt.token.sample.loginApp.dto.UserCreationResponse;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")

public interface UserMapper {

    User formToUser(UserCreationForm form);

    UserCreationResponse userToResponse(User user);
}
