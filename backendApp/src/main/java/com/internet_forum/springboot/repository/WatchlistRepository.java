package com.internet_forum.springboot.repository;

import com.internet_forum.springboot.mapper.WatchlistMapper;
import com.internet_forum.springboot.model.Watchlist;
import org.mapstruct.factory.Mappers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist, Long> {
    List<Watchlist> findByUserId(Long userId);

    List<Watchlist> findByTopicId(Long topicId);

    Optional<Object> findByTopicIdAndUserId(Long topicId, Long userId);
}
