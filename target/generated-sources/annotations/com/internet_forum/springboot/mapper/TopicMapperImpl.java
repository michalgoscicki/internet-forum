package com.internet_forum.springboot.mapper;

import com.internet_forum.springboot.dto.AuthorEntityResponseDto;
import com.internet_forum.springboot.dto.PostResponseDto;
import com.internet_forum.springboot.dto.TopicRequestDto;
import com.internet_forum.springboot.dto.TopicResponseDto;
import com.internet_forum.springboot.model.Post;
import com.internet_forum.springboot.model.Topic;
import com.internet_forum.springboot.model.UserEntity;
import com.internet_forum.springboot.model.ViolationReport;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-19T18:46:34+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class TopicMapperImpl implements TopicMapper {

    @Override
    public Topic requestDtoToEntity(TopicRequestDto topic) {
        if ( topic == null ) {
            return null;
        }

        String title = null;

        title = topic.title();

        UserEntity author = null;
        List<Post> posts = null;
        List<ViolationReport> violationReports = null;
        LocalDateTime createdAt = null;

        Topic topic1 = new Topic( title, author, posts, violationReports, createdAt );

        topic1.setContent( topic.content() );

        return topic1;
    }

    @Override
    public TopicResponseDto entityToResponseDto(Topic topic) {
        if ( topic == null ) {
            return null;
        }

        Long id = null;
        String title = null;
        String content = null;
        AuthorEntityResponseDto author = null;
        List<PostResponseDto> posts = null;

        id = topic.getId();
        title = topic.getTitle();
        content = topic.getContent();
        author = userEntityToAuthorEntityResponseDto( topic.getAuthor() );
        posts = postListToPostResponseDtoList( topic.getPosts() );

        TopicResponseDto topicResponseDto = new TopicResponseDto( id, title, content, author, posts );

        return topicResponseDto;
    }

    protected AuthorEntityResponseDto userEntityToAuthorEntityResponseDto(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        Long id = null;
        String username = null;

        id = userEntity.getId();
        username = userEntity.getUsername();

        AuthorEntityResponseDto authorEntityResponseDto = new AuthorEntityResponseDto( id, username );

        return authorEntityResponseDto;
    }

    protected PostResponseDto postToPostResponseDto(Post post) {
        if ( post == null ) {
            return null;
        }

        Long id = null;
        String content = null;
        AuthorEntityResponseDto author = null;

        id = post.getId();
        content = post.getContent();
        author = userEntityToAuthorEntityResponseDto( post.getAuthor() );

        PostResponseDto postResponseDto = new PostResponseDto( id, content, author );

        return postResponseDto;
    }

    protected List<PostResponseDto> postListToPostResponseDtoList(List<Post> list) {
        if ( list == null ) {
            return null;
        }

        List<PostResponseDto> list1 = new ArrayList<PostResponseDto>( list.size() );
        for ( Post post : list ) {
            list1.add( postToPostResponseDto( post ) );
        }

        return list1;
    }
}
