package com.internet_forum.springboot.mapper;

import com.internet_forum.springboot.dto.TopicRequestDto;
import com.internet_forum.springboot.dto.TopicResponseDto;
import com.internet_forum.springboot.model.Topic;

public interface TopicMapper {
    Topic requestDtoToEntity(TopicRequestDto topic);
    TopicResponseDto entityToResponseDto(Topic topic);
}
