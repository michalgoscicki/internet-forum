package com.internet_forum.springboot.mapper;

import com.internet_forum.springboot.dto.AuthorEntityResponseDto;
import com.internet_forum.springboot.dto.PostRequestDto;
import com.internet_forum.springboot.dto.PostResponseDto;
import com.internet_forum.springboot.model.Post;
import com.internet_forum.springboot.model.Topic;
import com.internet_forum.springboot.model.UserEntity;
import com.internet_forum.springboot.model.ViolationReport;
import java.time.LocalDateTime;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-19T18:46:34+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public Post requestDtoToEntity(PostRequestDto post) {
        if ( post == null ) {
            return null;
        }

        String content = null;

        content = post.content();

        Topic topic = null;
        UserEntity author = null;
        LocalDateTime createdAt = null;
        List<ViolationReport> violationReports = null;

        Post post1 = new Post( topic, author, content, createdAt, violationReports );

        return post1;
    }

    @Override
    public PostResponseDto entityToResponseDto(Post post) {
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
}
