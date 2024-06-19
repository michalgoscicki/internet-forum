package com.internet_forum.springboot.controller;

import com.internet_forum.springboot.dto.*;
import com.internet_forum.springboot.model.Topic;
import com.internet_forum.springboot.model.UserEntity;
import com.internet_forum.springboot.repository.PostRepository;
import com.internet_forum.springboot.repository.TopicRepository;
import com.internet_forum.springboot.repository.UserRepository;
import com.internet_forum.springboot.service.TopicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/topics")
@CrossOrigin(origins = "http://localhost:3000")
public class TopicController {

    private final TopicService topicService;
    private final TopicRepository topicRepository;
    private final PostRepository postRepository;



    public TopicController(TopicService topicService, TopicRepository topicRepository, PostRepository postRepository) {
        this.topicRepository = topicRepository;
        this.topicService = topicService;
        this.postRepository = postRepository;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TopicResponseDto createTopic(@Valid @RequestBody TopicRequestDto topic, Authentication authentication) {
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        Long userId = userEntity.getId();
        return topicService.createTopic(topic, userId);
    }
    @GetMapping
    public List<TopicResponseDto> getTopics() {
        return topicService.getTopics();

    }
    @GetMapping("/{id}")
    public ResponseEntity<TopicResponseDto> getTopicById(@PathVariable Long id) {
        Optional<TopicResponseDto> topic = topicService.getTopicById(id);
        return topic.map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("/{topic_id}")
    public ResponseEntity<String> deleteTopicById(@PathVariable("topic_id") long topicId) {
        if (topicRepository.existsById(topicId)) {
            topicRepository.deleteById(topicId);
            return new ResponseEntity<>("Topic deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Topic not found",HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{topic_id}/post")
    public TopicResponseDto addPost(@RequestBody PostRequestDto post, @PathVariable("topic_id") long topicId, Authentication authentication){
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        Long userId = userEntity.getId();
        return topicService.addPost(post, userId,topicId);
    }

    @DeleteMapping("/{topic_id}/post/{post_id}")
    public ResponseEntity<String> deletePostById(@PathVariable("post_id") long postId){
        if (postRepository.existsById(postId)) {
            postRepository.deleteById(postId);
            return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Post not found",HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{topic_id}/watchlist")
    public ResponseEntity<String> followTopic(@PathVariable("topic_id") long topicId, Authentication authentication){
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        Long userId = userEntity.getId();
        return topicService.followTopic(topicId, userId);
    }
}
