package com.internet_forum.springboot.dto;

import com.internet_forum.springboot.model.UserEntity;

public record TopicResponseDto(
        Long id,
        String title,
        UserEntity author
//        PostResponseDto posts
//        ViolationReportResponseDto violationReports
        ) {
}
