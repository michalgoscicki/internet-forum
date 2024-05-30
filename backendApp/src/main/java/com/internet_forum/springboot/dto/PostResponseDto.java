package com.internet_forum.springboot.dto;

import com.internet_forum.springboot.model.Topic;
import com.internet_forum.springboot.model.User;

import java.time.LocalDateTime;

public record PostResponseDto(
        Long id,
        Topic topic,
        User author,
        String content,
        LocalDateTime createdAt

) { }
