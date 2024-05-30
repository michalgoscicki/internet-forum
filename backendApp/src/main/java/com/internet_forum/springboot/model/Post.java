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
    private User author;

    private String content;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ViolationReport> violationReports = new ArrayList<>();

    protected Post(){}
    public Post(Topic topic, User author, String content, LocalDateTime createdAt, List<ViolationReport> violationReports) {
        this.topic = topic;
        this.author = author;
        this.content = content;
        this.createdAt = createdAt;
        this.violationReports = violationReports;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<ViolationReport> getViolationReports() {
        return violationReports;
    }

    public void setViolationReports(List<ViolationReport> violationReports) {
        this.violationReports = violationReports;
    }
}
