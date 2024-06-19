package com.internet_forum.springboot.service;

import com.internet_forum.springboot.Exceptions.LoggedInUserNotFound;
import com.internet_forum.springboot.Exceptions.TopicNotFoundException;
import com.internet_forum.springboot.Exceptions.UserDoNotExist;
import com.internet_forum.springboot.dto.*;
import com.internet_forum.springboot.mapper.PostMapper;
import com.internet_forum.springboot.mapper.TopicMapper;
import com.internet_forum.springboot.mapper.WatchlistMapper;
import com.internet_forum.springboot.model.Post;
import com.internet_forum.springboot.model.Topic;
import com.internet_forum.springboot.model.UserEntity;
import com.internet_forum.springboot.model.Watchlist;
import com.internet_forum.springboot.repository.PostRepository;
import com.internet_forum.springboot.repository.TopicRepository;
import com.internet_forum.springboot.repository.UserRepository;
import com.internet_forum.springboot.repository.WatchlistRepository;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TopicServiceImpl implements TopicService {

    private TopicRepository topicRepository;
    private TopicMapper topicMapper;
    private WatchlistMapper watchlistMapper;
    private UserRepository userRepository;
    private WatchlistRepository watchlistRepository;
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
    public TopicResponseDto updateTopic(Long id, TopicRequestDto topicRequestDto) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new LoggedInUserNotFound());
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new TopicNotFoundException());

        topic.setTitle(topicRequestDto.title());
        topic.setContent(topicRequestDto.content());
        topic.setModifiedAt(LocalDateTime.now());

        Topic updatedTopic = topicRepository.save(topic);
        return topicMapper.entityToResponseDto(updatedTopic);
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
    public void deleteTopicById(Long id) {
        topicRepository.deleteById(id);
    }

    @Override
    public TopicResponseDto addPost(PostRequestDto postRequestDto, Long userId, Long topicId) {
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

    @Override
    public ResponseEntity<String> followTopic(Long topicId, Long userId){
        Watchlist watchlist = new Watchlist();
        watchlist.setUser(userRepository.findById(userId).get());
        watchlist.setTopic(topicRepository.findById(topicId).get());

        watchlistRepository.save(watchlist);

        String responseMessage = "Użytkownik o ID: " + userId + " zaobserwował temat o ID: " + topicId;
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @Override
    public void deletePost(Long topicId, Long postId) {

    }

}
