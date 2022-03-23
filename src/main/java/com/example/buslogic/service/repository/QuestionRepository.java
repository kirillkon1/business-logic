package com.example.buslogic.service.repository;

import com.example.buslogic.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("select q from Question q where q.userId = ?1 and q.status = 'APPROVED'")
    List<Question> getAllByUserId(Long userId);

    @Query("select q from Question q where q.status = 'ON_MODERATION'")
    List<Question> getListToModerate();
}