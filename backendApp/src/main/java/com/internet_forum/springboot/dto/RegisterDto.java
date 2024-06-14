package com.internet_forum.springboot.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private String email;
    private String name;
    private String surname;
    private String username;
    private String password;
}
