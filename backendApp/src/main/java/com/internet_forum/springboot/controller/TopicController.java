package com.internet_forum.springboot.controller;

import com.internet_forum.springboot.dto.TopicRequestDto;
import com.internet_forum.springboot.dto.TopicResponseDto;
import com.internet_forum.springboot.model.Topic;
import com.internet_forum.springboot.service.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/topics")
@CrossOrigin(origins = "http://localhost:3000")
public class TopicController {

    private TopicService topicService;

    //TODO pobrac id usera z authentication.getPricipal()
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TopicResponseDto createTopic(@RequestBody TopicRequestDto topic, @RequestParam Long userId) {
        return topicService.createTopic(topic, userId);
    }
}
