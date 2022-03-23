package com.example.buslogic.admin.service;


import com.example.buslogic.model.Question;
import com.example.buslogic.model.Status;
import com.example.buslogic.service.repository.QuestionRepository;
import com.example.buslogic.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class AdminQuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public List<Question> getListToApprove() {
        return questionRepository.getListToModerate();
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
        log.info("admin {} deleted question with id #{}", Util.getUserFromContext(), id);
    }

    public void approveQuestion(Long id) {
        Question q = questionRepository.getById(id);
        q.setStatus(Status.APPROVED);
        questionRepository.save(q);
        log.info("admin {} approved question with id #{}", Util.getUserFromContext(), id);

    }


}
