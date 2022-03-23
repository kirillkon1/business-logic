package com.example.buslogic.admin.service;

import com.example.buslogic.model.Answer;
import com.example.buslogic.model.Status;
import com.example.buslogic.service.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminAnswerService {

    @Autowired
    AnswerRepository answerRepository;

    public void approve(Long id){
        Answer answer = answerRepository.getById(id);
        answer.setStatus(Status.APPROVED);
        answerRepository.save(answer);
    }

    public void delete(Long id){
        answerRepository.deleteById(id);
    }

    public List<Answer> getAnswersToModerate(){
        return answerRepository.getAllToModerate();
    }

}
