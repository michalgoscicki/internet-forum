package com.internet_forum.springboot.service;

import com.internet_forum.springboot.dto.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface TopicService {
    TopicResponseDto createTopic(TopicRequestDto topicRequestDto, Long userId);
    List<TopicResponseDto> getTopics();

    Optional<TopicResponseDto> getTopicById(Long id);

    void deleteTopicById(Long id);

    TopicResponseDto addPost(PostRequestDto postRequestDto, Long userId, Long topicId);

    ResponseEntity<String> followTopic(Long topicId, Long userId);



}

