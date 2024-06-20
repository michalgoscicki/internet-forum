package com.internet_forum.springboot.service;

import com.internet_forum.springboot.Exceptions.LoggedInUserNotFound;
import com.internet_forum.springboot.Exceptions.PostNotFoundException;
import com.internet_forum.springboot.dto.PostRequestDto;
import com.internet_forum.springboot.dto.PostResponseDto;
import com.internet_forum.springboot.mapper.PostMapper;
import com.internet_forum.springboot.model.Post;
import com.internet_forum.springboot.model.UserEntity;
import com.internet_forum.springboot.repository.PostRepository;
import com.internet_forum.springboot.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {


    private UserRepository userRepository;
    private PostRepository postRepository;
    private PostMapper postMapper;


    @Override
    public PostResponseDto updatePost(Long id, PostRequestDto postRequestDto) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new LoggedInUserNotFound());
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException());


        post.setContent(postRequestDto.content());
        post.setModifiedAt(LocalDateTime.now());

        Post updatedPost = postRepository.save(post);
        return postMapper.entityToResponseDto(updatedPost);

    }

}
