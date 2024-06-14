package com.internet_forum.springboot.dto;

import com.internet_forum.springboot.model.UserEntity;
import lombok.Data;

@Data
public class AuthResponseDTO {
    private String accessToken;
    private String tokenType = "Bearer ";
    private UserEntity user;

    public AuthResponseDTO(String accessToken, UserEntity user) {
        this.accessToken = accessToken;
        this.user = user;
    }
}
