package com.example.buslogic.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "vote")
@Data
public class Vote extends BaseEntity{

    @Column(name = "answer_id")
    Long answerId;

    @Column(name = "user_id")
    Long userId;

    @Column(name = "is_positive")
    boolean isPositive;
}
