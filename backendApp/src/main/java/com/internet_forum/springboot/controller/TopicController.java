package com.internet_forum.springboot.controller;

import com.internet_forum.springboot.dto.*;
import com.internet_forum.springboot.model.UserEntity;
import com.internet_forum.springboot.repository.PostRepository;
import com.internet_forum.springboot.repository.TopicRepository;
import com.internet_forum.springboot.service.PostService;
import com.internet_forum.springboot.service.TopicService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.kerberos.KerberosKey;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/topics")
@CrossOrigin(origins = "http://localhost:3000")
public class TopicController {

    private final TopicService topicService;
    private final TopicRepository topicRepository;
    private final PostRepository postRepository;
    private final PostService postService;




    public TopicController(TopicService topicService, TopicRepository topicRepository, PostRepository postRepository, PostService postService) {
        this.topicRepository = topicRepository;
        this.topicService = topicService;
        this.postRepository = postRepository;
        this.postService = postService;
    }

//    TOPIC ENDPOINTS
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

    @PutMapping("/{id}")
    public ResponseEntity<TopicResponseDto> updateTopic(@PathVariable Long id, @RequestBody TopicRequestDto topicRequestDto) {
        TopicResponseDto updatedTopic = topicService.updateTopic(id, topicRequestDto);
        return ResponseEntity.ok(updatedTopic);
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

//    POST ENDPOINTS

    @PostMapping("/{topic_id}/post")
    public ResponseEntity<TopicResponseDto> addPost(@RequestBody PostRequestDto post, @PathVariable("topic_id") long topicId, Authentication authentication){
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        Long userId = userEntity.getId();
        TopicResponseDto response = topicService.addPost(post, userId, topicId);
        return ResponseEntity.ok(response);
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
    @PutMapping("/{topic_id}/post/{post_id}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long topic_id, @PathVariable Long post_id, @RequestBody PostRequestDto postRequestDto) {
        PostResponseDto updatedPost = postService.updatePost(post_id, postRequestDto);
        return ResponseEntity.ok(updatedPost);
    }


//    WATCHLIST ENDPOINTS
    @PostMapping("/{topic_id}/watchlist")
    public ResponseEntity<String> followTopic(@PathVariable("topic_id") long topicId, Authentication authentication){
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        Long userId = userEntity.getId();
        return topicService.followTopic(topicId, userId);
    }


    @PostMapping("/{topic_id}/unfollow")
    public ResponseEntity<String> unfollowTopic(@PathVariable("topic_id") Long topicId, Authentication authentication) {
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        return topicService.unfollowTopic(topicId, userEntity.getId());
    }

    @GetMapping("/{topic_id}/followers")
    public ResponseEntity<List<UserResponseDto>> getFollowers(@PathVariable("topic_id") Long topicId) {
        List<UserResponseDto> followers = topicService.getFollowersByTopicId(topicId);
        return ResponseEntity.ok(followers);
    }
}
