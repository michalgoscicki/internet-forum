package com.internet_forum.springboot.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ViolationReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity reporter;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = true)
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = true)
    private Post post;

    private String reason;
    private LocalDateTime reportedAt;

    protected ViolationReport(){}
    public ViolationReport(UserEntity reporter, Topic topic, Post post, String reason, LocalDateTime reportedAt) {
        this.reporter = reporter;
        this.topic = topic;
        this.post = post;
        this.reason = reason;
        this.reportedAt = reportedAt;
    }


}

