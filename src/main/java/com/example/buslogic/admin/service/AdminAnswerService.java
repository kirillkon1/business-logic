package com.example.buslogic.admin.service;

import com.example.buslogic.activeMQ.MessageSender;
import com.example.buslogic.model.Answer;
import com.example.buslogic.model.Status;
import com.example.buslogic.service.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminAnswerService {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    MessageSender messageSender;

    @Transactional
    public void approve(Long id) {
        Answer answer = answerRepository.getById(id);
        answer.setStatus(Status.APPROVED);
        answerRepository.save(answer);
        messageSender.sendQueue(LocalDateTime.now() + ": Answer #" + answer.getId() + " of user#" + answer.getUserId() + "has been approved");
    }

    @Transactional
    public void decline(Long id) {
        Answer answer = answerRepository.getById(id);
        answer.setStatus(Status.DECLINE);
        answerRepository.save(answer);
        messageSender.sendQueue(LocalDateTime.now() + ": Answer #" + answer.getId() + " of user#" + answer.getUserId() + "has been decline");
    }

    public List<Answer> getAnswersToModerate() {
        return answerRepository.getAllToModerate();
    }


}
