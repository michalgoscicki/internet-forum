package com.internet_forum.springboot.mapper;

import com.internet_forum.springboot.dto.AuthorEntityResponseDto;
import com.internet_forum.springboot.dto.PostResponseDto;
import com.internet_forum.springboot.dto.TopicResponseDto;
import com.internet_forum.springboot.model.Watchlist;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-20T02:43:12+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class WatchlistMapperImpl implements WatchlistMapper {

    @Override
    public TopicResponseDto toDto(Watchlist entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;

        id = entity.getId();

        String title = null;
        String content = null;
        AuthorEntityResponseDto author = null;
        List<PostResponseDto> posts = null;

        TopicResponseDto topicResponseDto = new TopicResponseDto( id, title, content, author, posts );

        return topicResponseDto;
    }

    @Override
    public List<TopicResponseDto> toDtoList(List<Watchlist> entities) {
        if ( entities == null ) {
            return null;
        }

        List<TopicResponseDto> list = new ArrayList<TopicResponseDto>( entities.size() );
        for ( Watchlist watchlist : entities ) {
            list.add( toDto( watchlist ) );
        }

        return list;
    }
}
