package com.internet_forum.springboot.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ViolationReport extends BaseDatedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity reporter;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = true)
    private Topic topic;

    private String reason;
    private LocalDateTime reportedAt;

    public ViolationReport(){}
    public ViolationReport(UserEntity reporter, Topic topic, String reason, LocalDateTime reportedAt) {
        this.reporter = reporter;
        this.topic = topic;
        this.reason = reason;
        this.reportedAt = reportedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getReporter() {
        return reporter;
    }

    public void setReporter(UserEntity reporter) {
        this.reporter = reporter;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
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

