package com.internet_forum.springboot.dto;


import lombok.Data;


public record UserResponseDto(
        Long id,
        String username,
        String email

)
{}
