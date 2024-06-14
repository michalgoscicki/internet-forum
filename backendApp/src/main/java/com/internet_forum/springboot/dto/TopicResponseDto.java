package com.internet_forum.springboot.dto;

import com.internet_forum.springboot.model.Topic;
import com.internet_forum.springboot.model.UserEntity;

public record TopicResponseDto(
        Long id,
        String title,
        String content,
        TopicAuthorEntityResponseDto author
//        PostResponseDto posts
//        ViolationReportResponseDto violationReports
        ) {
}
