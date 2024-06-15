package com.internet_forum.springboot.mapper;

import com.internet_forum.springboot.dto.PostRequestDto;
import com.internet_forum.springboot.dto.PostResponseDto;

import com.internet_forum.springboot.model.Post;
import org.mapstruct.Mapper;

@Mapper
public interface PostMapper {
    Post requestDtoToEntity(PostRequestDto post);
    PostResponseDto entityToResponseDto(Post post);
}
