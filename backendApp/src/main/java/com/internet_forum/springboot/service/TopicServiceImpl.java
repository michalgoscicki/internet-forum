package com.internet_forum.springboot.service;

import com.internet_forum.springboot.dto.PostRequestDto;
import com.internet_forum.springboot.dto.PostResponseDto;
import com.internet_forum.springboot.dto.TopicRequestDto;
import com.internet_forum.springboot.dto.TopicResponseDto;
import com.internet_forum.springboot.mapper.PostMapper;
import com.internet_forum.springboot.mapper.TopicMapper;
import com.internet_forum.springboot.model.Post;
import com.internet_forum.springboot.model.Topic;
import com.internet_forum.springboot.model.UserEntity;
import com.internet_forum.springboot.repository.PostRepository;
import com.internet_forum.springboot.repository.TopicRepository;
import com.internet_forum.springboot.repository.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private PostRepository postRepository;
    private PostMapper postMapper;


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

    @Override
    public void deleteTopic() {
        topicRepository.deleteAll();
    }

    @Override
    public void deleteTopicById(Long id) {
        topicRepository.deleteById(id);
    }

    @Override
    public TopicResponseDto addPost(PostRequestDto postRequestDto, Long userId, Long topicId){
        Post post = postMapper.requestDtoToEntity(postRequestDto);
        Optional<Topic> topicOptional = topicRepository.findById(topicId);

//        if (!topicOptional.isPresent()) {
//            throw new ResourceNotFoundException("Topic not found with id: " + topicId);
//        }

        Topic topic = topicOptional.get();
        Optional<UserEntity> userOptional = userRepository.findById(userId);

        post.setTopic(topic);
        post.setAuthor(userOptional.get());
        topic.getPosts().add(post);

        postRepository.save(post);


        return topicMapper.entityToResponseDto(topic);
    }

}
