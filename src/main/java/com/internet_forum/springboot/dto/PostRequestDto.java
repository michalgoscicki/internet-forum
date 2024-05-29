package com.internet_forum.springboot.dto;

import com.internet_forum.springboot.model.Topic;
import com.internet_forum.springboot.model.User;

public record PostRequestDto(
        Topic topic,
        User author,
        String content
) {}
