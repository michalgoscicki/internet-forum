package com.internet_forum.springboot.dto;

import com.internet_forum.springboot.model.Post;

import java.util.List;

public record TopicResponseDto(
        Long id,
        String title,
        String content,
        AuthorEntityResponseDto author,
        List<PostResponseDto> posts
//        ViolationReportResponseDto violationReports
        ) {
}
