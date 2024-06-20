package com.internet_forum.springboot.dto;

import com.internet_forum.springboot.model.Topic;

import java.util.List;

public class FollowedTopicResponseDto {
    Long id;
    String title;
    String content;
    AuthorEntityResponseDto author;
    List<PostResponseDto> posts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AuthorEntityResponseDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntityResponseDto author) {
        this.author = author;
    }

    public List<PostResponseDto> getPosts() {
        return posts;
    }

    public void setPosts(List<PostResponseDto> posts) {
        this.posts = posts;
    }
}
