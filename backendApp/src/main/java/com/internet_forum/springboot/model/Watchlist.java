package com.internet_forum.springboot.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Watchlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    private LocalDateTime watchedAt;

    protected Watchlist(){}
    public Watchlist(UserEntity user, Topic topic, LocalDateTime watchedAt) {
        this.user = user;
        this.topic = topic;
        this.watchedAt = watchedAt;
    }


}
