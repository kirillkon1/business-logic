package com.example.buslogic.model;

import javax.persistence.*;

@Entity
@Table(name = "answer")
public class Answer extends BaseEntity{

    @Column(name = "content", length = 1000, nullable = false)
    private String content;
    
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "question_id", nullable = false)
    private Long questionId;

    @Column(name = "rating")
    private Long rating = 0L;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status = Status.APPROVED;
}
