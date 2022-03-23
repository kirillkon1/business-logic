package com.example.buslogic.service.repository;

import com.example.buslogic.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query("select q from Question q where q.status = 'ON_MODERATION'")
    List<Answer> getAllToModerate();

    @Query("select a from Answer a where a.status = 'APPROVED' and a.questionId = ?1")
    List<Answer> getAllByQuestionId(Long id);

}