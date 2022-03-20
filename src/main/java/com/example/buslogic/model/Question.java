package com.example.buslogic.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "question")
@Data
public class Question extends BaseEntity {

    @Column(name = "title", length = 100, nullable = false)
    private Long title;

    @Column(name = "content", length = 1000, nullable = false)
    private Long content;

    @Column(name = "topic_id", nullable = false)
    private Long topicId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;
}
