package com.internet_forum.springboot.dto;

import com.internet_forum.springboot.model.User;

public record TopicResponseDto(
        Long id,
        String title,
        User author,
        PostResponseDto posts
//        ViolationReportResponseDto violationReports
        ) {
}
