package com.internet_forum.springboot.service;

import com.internet_forum.springboot.dto.*;
import com.internet_forum.springboot.model.Topic;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface TopicService {
    TopicResponseDto createTopic(TopicRequestDto topicRequestDto, Long userId);
    List<TopicResponseDto> getTopics();
    Optional<TopicResponseDto> getTopicById(Long id);

    TopicResponseDto updateTopic(Long id,TopicRequestDto topicRequestDto);

    void deleteTopicById(Long id);

    TopicResponseDto addPost(PostRequestDto postRequestDto, Long userId, Long topicId);

    ResponseEntity<String> followTopic(Long topicId, Long userId);

    void deletePost(Long topicId, Long postId);


}

