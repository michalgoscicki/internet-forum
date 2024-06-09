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
    private UserEntity author;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ViolationReport> violationReports = new ArrayList<>();

    protected Topic(){}
    public Topic(String title, UserEntity author, List<Post> posts, List<ViolationReport> violationReports) {
        this.title = title;
        this.author = author;
        this.posts = posts;
        this.violationReports = violationReports;
    }


}
