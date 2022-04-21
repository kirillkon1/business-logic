package com.example.buslogic.scheduling;

import com.example.buslogic.activeMQ.MessageSender;
import com.example.buslogic.service.AnswerService;
import com.example.buslogic.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class Schedules {

    @Autowired MessageSender messageSender;
    @Autowired QuestionService questionService;
    @Autowired AnswerService answerService;

    @Transactional
    @Scheduled(fixedDelay = 24 * 60 * 60 * 1000)
    public void setAnswerDecline(){
        answerService.setAnswerTimeout();
        messageSender.sendAdminQueue("Была проведена очистка от Answer " +
                "со статусом ON_MODERATION");
    }

    @Transactional
    @Scheduled(fixedDelay = 24 * 60 * 60 * 1000)
    public void setQuestionDecline(){
        questionService.setQuestionTimeout();
        messageSender.sendAdminQueue("Была проведена очистка от Question " +
                "со статусом ON_MODERATION");
    }


}
