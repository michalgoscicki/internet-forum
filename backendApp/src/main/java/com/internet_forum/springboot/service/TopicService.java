package com.internet_forum.springboot.service;

import com.internet_forum.springboot.dto.TopicRequestDto;
import com.internet_forum.springboot.dto.TopicResponseDto;

public interface TopicService {
    TopicResponseDto createTopic(TopicRequestDto topicRequestDto, Long userId);
}
