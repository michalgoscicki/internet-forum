package com.internet_forum.springboot.dto;

import com.internet_forum.springboot.model.Topic;
import com.internet_forum.springboot.model.UserEntity;

import java.time.LocalDateTime;

public record PostResponseDto(
        Long id,
        String content

) { }
