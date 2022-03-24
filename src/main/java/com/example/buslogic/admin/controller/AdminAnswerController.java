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

    @GetMapping()
    public List<Answer> getAnswersToModerate() {
        return answerService.getAnswersToModerate();
    }

    @PutMapping(path = "/{id}")
    public void approve(@PathVariable Long id) {
        answerService.approve(id);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        answerService.delete(id);
    }
}
