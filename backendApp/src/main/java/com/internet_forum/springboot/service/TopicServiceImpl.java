package com.internet_forum.springboot.service;

import com.internet_forum.springboot.Exceptions.LoggedInUserNotFound;
import com.internet_forum.springboot.Exceptions.TopicNotFoundException;
import com.internet_forum.springboot.Exceptions.UserDoNotExist;
import com.internet_forum.springboot.dto.*;
import com.internet_forum.springboot.mapper.*;
import com.internet_forum.springboot.model.*;
import com.internet_forum.springboot.repository.*;
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
import java.util.stream.Collectors;

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
    private UserMapper userMapper;
    private EmailService emailService;
    private ViolationReportMapper violationReportMapper;
    private ViolationReportRepository violationReportRepository;


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
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new TopicNotFoundException());
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new LoggedInUserNotFound());

        post.setTopic(topic);
        post.setAuthor(user);
        topic.getPosts().add(post);
        postRepository.save(post);

        List<UserResponseDto> followers = getFollowersByTopicId(topicId);
        for (UserResponseDto follower : followers) {
            emailService.sendEmail(follower.email(), "New post in: " + topic.getTitle(),
                    "A new post has been added by " + user.getUsername() + ": " + post.getContent());
        }

        return topicMapper.entityToResponseDto(topic);
    }

    @Override
    public ResponseEntity<String> followTopic(Long topicId, Long userId) {
        Watchlist watchlist = new Watchlist();
        watchlist.setUser(userRepository.findById(userId).get());
        watchlist.setTopic(topicRepository.findById(topicId).get());
        watchlistRepository.save(watchlist);
        String responseMessage = "Użytkownik o ID: " + userId + " zaobserwował temat o ID: " + topicId;
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> reportTopic(ViolationReportRequestDto violationReportRequestDto, Long topicId, Long userId){
        ViolationReport violationReport = violationReportMapper.requestDtoToEntity(violationReportRequestDto);
        violationReport.setReporter(userRepository.findById(userId).get());
        violationReport.setTopic(topicRepository.findById(topicId).get());
        violationReport.setReason(violationReport.getReason());
        violationReportRepository.save(violationReport);
        String responseMessage = "Użytkownik o ID: " + userId + " zgłosił temat o ID: " + topicId;
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<String> unfollowTopic(Long topicId, Long userId) {
        Watchlist watchlist = (Watchlist) watchlistRepository.findByTopicIdAndUserId(topicId, userId)
                .orElseThrow(() -> new RuntimeException("Watchlist entry not found for topicId: " + topicId + " and userId: " + userId));

        watchlistRepository.delete(watchlist);
        return new ResponseEntity<>("User with ID:" + userId + " unfollow topic with ID: " + topicId, HttpStatus.OK);

    }

    @Override
    public void deletePost(Long topicId, Long postId) {}

    @Override
    public List<UserResponseDto> getFollowersByTopicId(Long topicId) {
        List<Watchlist> watchlist = watchlistRepository.findByTopicId(topicId);
        return watchlist.stream()
                .map(watchlistItem -> userMapper.entityToResponseDto(watchlistItem.getUser()))
                .collect(Collectors.toList());
    }
}
