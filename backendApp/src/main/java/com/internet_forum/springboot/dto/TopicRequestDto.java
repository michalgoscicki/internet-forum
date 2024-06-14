package com.internet_forum.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TopicRequestDto(

        @NotBlank(message = "Title is mandatory")
        @Size(max = 150, message = "Title can have at most 150 characters")
        @Size(min = 2, message = "Title can have at least 2 chaacters")
        String title,

        @NotBlank(message = "Content is mandatory")
        @Size(max = 1500, message = "Content can have at most 1500 characters")
        @Size(min = 10, message = "Content can have at least 10 characters")
        String content
) {
}
