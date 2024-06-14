package com.internet_forum.springboot.mapper;

import com.internet_forum.springboot.dto.TopicAuthorEntityResponseDto;
import com.internet_forum.springboot.dto.TopicRequestDto;
import com.internet_forum.springboot.dto.TopicResponseDto;
import com.internet_forum.springboot.model.Post;
import com.internet_forum.springboot.model.Topic;
import com.internet_forum.springboot.model.UserEntity;
import com.internet_forum.springboot.model.ViolationReport;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-14T03:23:31+0200",
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

        Topic topic1 = new Topic( title, author, posts, violationReports );

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
        TopicAuthorEntityResponseDto author = null;

        id = topic.getId();
        title = topic.getTitle();
        content = topic.getContent();
        author = userEntityToTopicAuthorEntityResponseDto( topic.getAuthor() );

        TopicResponseDto topicResponseDto = new TopicResponseDto( id, title, content, author );

        return topicResponseDto;
    }

    protected TopicAuthorEntityResponseDto userEntityToTopicAuthorEntityResponseDto(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        Long id = null;
        String username = null;

        id = userEntity.getId();
        username = userEntity.getUsername();

        TopicAuthorEntityResponseDto topicAuthorEntityResponseDto = new TopicAuthorEntityResponseDto( id, username );

        return topicAuthorEntityResponseDto;
    }
}
