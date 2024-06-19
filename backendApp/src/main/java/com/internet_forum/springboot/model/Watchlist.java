package com.internet_forum.springboot.model;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Watchlist extends BaseDatedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;


    public Watchlist(){}
    public Watchlist(UserEntity user, Topic topic) {
        this.user = user;
        this.topic = topic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

}
