package com.example.buslogic.admin.service;


import com.example.buslogic.activeMQ.MessageSender;
import com.example.buslogic.model.Question;
import com.example.buslogic.model.Status;
import com.example.buslogic.service.repository.QuestionRepository;
import com.example.buslogic.security.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Service
public class AdminQuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    MessageSender messageSender;

    public List<Question> getListToApprove() {
        return questionRepository.getListToModerate();
    }

    public void decline(Long id) {
        Question q = questionRepository.getById(id);
        q.setStatus(Status.DECLINE);
        questionRepository.save(q);
//        log.info("admin {} deleted question with id #{}", Util.getUserFromContext(), id);
        messageSender.sendQueue(LocalDateTime.now() + ": Question #" + q.getId() + " of user#" + q.getUserId() + "has been approved");
    }

    public void approve(Long id) {
        Question q = questionRepository.getById(id);
        q.setStatus(Status.APPROVED);
        questionRepository.save(q);
//        log.info("admin {} approved question with id #{}", Util.getUserFromContext(), id);
        messageSender.sendQueue(LocalDateTime.now() + ": Question #" + q.getId() + " of user#" + q.getUserId() + "has been decline");

    }


}
