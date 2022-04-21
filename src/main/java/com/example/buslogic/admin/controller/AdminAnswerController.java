package com.example.buslogic.admin.controller;

import com.example.buslogic.admin.service.AdminAnswerService;
import com.example.buslogic.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "admin/answer")
public class AdminAnswerController {

    @Autowired
    AdminAnswerService answerService;

    @GetMapping(path="/approveList")
    public List<Answer> getAnswersToModerate() {
        return answerService.getAnswersToModerate();
    }

    @PutMapping(path = "/{id}/approve")
    public void approve(@PathVariable Long id) {
        answerService.approve(id);
    }

    @PutMapping(path = "/{id}/decline")
    public void delete(@PathVariable Long id) {
        answerService.decline(id);
    }
}
