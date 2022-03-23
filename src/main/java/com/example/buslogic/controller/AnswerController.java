package com.example.buslogic.controller;

import com.example.buslogic.model.Answer;
import com.example.buslogic.model.dto.AnswerDTO;
import com.example.buslogic.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping(path = "answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;


    @GetMapping(path = "/quest/{id}")
    public List<Answer> getAnswersByQuestions(@PathVariable Long id) {
        return answerService.getAnswersByQuestion(id);
    }

    @PostMapping
    public Answer createQuestion(@RequestBody AnswerDTO dto) {
        return answerService.createAnswer(dto);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteQuestion(@PathVariable Long id) throws AccessDeniedException {
        answerService.deleteAnswer(id);
    }
}
