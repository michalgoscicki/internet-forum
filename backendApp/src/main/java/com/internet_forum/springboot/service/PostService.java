package com.internet_forum.springboot.service;

import com.internet_forum.springboot.dto.PostRequestDto;
import com.internet_forum.springboot.dto.PostResponseDto;

import java.util.Optional;


public interface PostService {

    PostResponseDto updatePost(Long id, PostRequestDto PostRequestDto);
}
