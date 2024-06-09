package com.internet_forum.springboot.mapper;

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
    date = "2024-06-09T04:23:16+0200",
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
        UserEntity author = null;

        title = topic.title();
        author = topic.author();

        List<Post> posts = null;
        List<ViolationReport> violationReports = null;

        Topic topic1 = new Topic( title, author, posts, violationReports );

        return topic1;
    }

    @Override
    public TopicResponseDto entityToResponseDto(Topic topic) {
        if ( topic == null ) {
            return null;
        }

        Long id = null;
        String title = null;
        UserEntity author = null;

        TopicResponseDto topicResponseDto = new TopicResponseDto( id, title, author );

        return topicResponseDto;
    }
}
