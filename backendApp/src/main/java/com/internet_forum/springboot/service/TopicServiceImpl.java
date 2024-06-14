package com.internet_forum.springboot.service;

import com.internet_forum.springboot.dto.TopicRequestDto;
import com.internet_forum.springboot.dto.TopicResponseDto;
import com.internet_forum.springboot.mapper.TopicMapper;
import com.internet_forum.springboot.model.Topic;
import com.internet_forum.springboot.model.UserEntity;
import com.internet_forum.springboot.repository.TopicRepository;
import com.internet_forum.springboot.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TopicServiceImpl implements TopicService {

    private TopicRepository topicRepository;
    private TopicMapper topicMapper;
    private UserRepository userRepository;

    @Override
    public TopicResponseDto createTopic(TopicRequestDto topicRequestDto, @RequestParam Long userId) {
        Optional<UserEntity> userOptional = userRepository.findById(userId);
        Topic topic = topicMapper.requestDtoToEntity(topicRequestDto);
        topic.setAuthor(userOptional.get());
        Topic savedTopic = topicRepository.save(topic);
        return topicMapper.entityToResponseDto(savedTopic);
    }

    @Override
    public List<TopicResponseDto> getTopics() {
        return topicRepository.findAll()
                .stream()
                .map(topicMapper::entityToResponseDto)
                .toList();
    }

    @Override
    public Optional<TopicResponseDto> getTopicById(Long id) {
        return topicRepository.findById(id)
                .map(topicMapper::entityToResponseDto);
    }
}
