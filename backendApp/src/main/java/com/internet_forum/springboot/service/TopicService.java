package com.internet_forum.springboot.service;

import com.internet_forum.springboot.dto.TopicRequestDto;
import com.internet_forum.springboot.dto.TopicResponseDto;

import java.util.List;
import java.util.Optional;

public interface TopicService {
    TopicResponseDto createTopic(TopicRequestDto topicRequestDto, Long userId);
    List<TopicResponseDto> getTopics();

    Optional<TopicResponseDto> getTopicById(Long id);
}

