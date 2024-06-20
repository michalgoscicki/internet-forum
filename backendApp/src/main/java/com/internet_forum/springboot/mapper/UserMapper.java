package com.internet_forum.springboot.mapper;

import com.internet_forum.springboot.dto.UserResponseDto;
import com.internet_forum.springboot.model.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto entityToResponseDto(UserEntity user) {
        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail());
    }
}

