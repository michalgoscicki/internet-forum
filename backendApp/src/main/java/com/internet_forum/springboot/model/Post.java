package com.internet_forum.springboot.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity author;

    private String content;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ViolationReport> violationReports = new ArrayList<>();

    protected Post(){}
    public Post(Topic topic, UserEntity author, String content, LocalDateTime createdAt, List<ViolationReport> violationReports) {
        this.topic = topic;
        this.author = author;
        this.content = content;
        this.createdAt = createdAt;
        this.violationReports = violationReports;
    }

}
