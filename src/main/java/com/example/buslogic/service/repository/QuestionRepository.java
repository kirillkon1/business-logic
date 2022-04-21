package com.example.buslogic.service.repository;

import com.example.buslogic.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("select q from Question q where q.userId = ?1")
    List<Question> getAllByUserId(Long userId);

    @Query("select q from Question q where q.status = 'ON_MODERATION'")
    List<Question> getListToModerate();

    @Query("select q from Question q where q.status = 'APPROVED'")
    List<Question> getAllQuestions();

    @Modifying
    @Query(value = "update Question q set q.status = 'DECLINE' where q.status = 'ON_MODERATION' and q.created < :date")
    void setTimeoutQuestion(Date date);
}