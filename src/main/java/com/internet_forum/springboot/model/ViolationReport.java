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
    private User reporter;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = true)
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = true)
    private Post post;

    private String reason;
    private LocalDateTime reportedAt;

    protected ViolationReport(){}
    public ViolationReport(User reporter, Topic topic, Post post, String reason, LocalDateTime reportedAt) {
        this.reporter = reporter;
        this.topic = topic;
        this.post = post;
        this.reason = reason;
        this.reportedAt = reportedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getReportedAt() {
        return reportedAt;
    }

    public void setReportedAt(LocalDateTime reportedAt) {
        this.reportedAt = reportedAt;
    }
}

