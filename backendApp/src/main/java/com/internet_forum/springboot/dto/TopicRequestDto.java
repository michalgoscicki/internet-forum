package com.internet_forum.springboot.dto;

import com.internet_forum.springboot.model.UserEntity;

public record TopicRequestDto(
        String title,
        String content
) {}
