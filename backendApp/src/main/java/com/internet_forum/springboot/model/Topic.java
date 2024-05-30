package com.internet_forum.springboot.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ViolationReport> violationReports = new ArrayList<>();

    protected Topic(){}
    public Topic(String title, User author, List<Post> posts, List<ViolationReport> violationReports) {
        this.title = title;
        this.author = author;
        this.posts = posts;
        this.violationReports = violationReports;
    }

    public String getContent() {return content;}

    public void setContent(String content) {this.content = content;}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<ViolationReport> getViolationReports() {
        return violationReports;
    }

    public void setViolationReports(List<ViolationReport> violationReports) {
        this.violationReports = violationReports;
    }
}
