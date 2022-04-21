package com.example.buslogic.service.repository;

import com.example.buslogic.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query("select a from Answer a where a.status = 'ON_MODERATION'")
    List<Answer> getAllToModerate();

    @Query("select a from Answer a where a.status = 'APPROVED' and a.questionId = ?1")
    List<Answer> getAllByQuestionId(Long id);

    @Modifying
    @Query(value = "update Answer a set a.status = 'DECLINE' where a.status = 'ON_MODERATION' and a.created < :date")
    void setTimeoutQuestion(Date date);
}