package com.example.buslogic.controller;

import com.example.buslogic.model.dto.QuestionDTO;
import com.example.buslogic.model.Question;
import com.example.buslogic.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping(path = "/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    @GetMapping
    public List<Question> getUserQuestions() {
        return questionService.getUserQuestions();
    }

    @GetMapping(path = "/all")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @PostMapping
    public Question createQuestion(@RequestBody QuestionDTO dto) {
        return questionService.createQuestion(dto);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteQuestion(@PathVariable Long id) throws AccessDeniedException {
        questionService.deleteQuestion(id);
    }


}
