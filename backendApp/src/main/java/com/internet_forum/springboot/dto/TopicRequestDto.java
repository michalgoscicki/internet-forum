package com.internet_forum.springboot.dto;

import com.internet_forum.springboot.model.User;

public record TopicRequestDto(
        String title,
        String content,
        User author
) {}
