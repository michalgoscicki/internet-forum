package com.internet_forum.springboot.mapper;

import com.internet_forum.springboot.dto.TopicResponseDto;
import com.internet_forum.springboot.model.Watchlist;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface WatchlistMapper {
    WatchlistMapper INSTANCE = Mappers.getMapper(WatchlistMapper.class);

    TopicResponseDto toDto(Watchlist entity);

    List<TopicResponseDto> toDtoList(List<Watchlist> entities);
}