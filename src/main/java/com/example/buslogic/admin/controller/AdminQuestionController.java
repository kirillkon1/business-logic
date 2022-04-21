package com.example.buslogic.admin.controller;

import com.example.buslogic.admin.service.AdminQuestionService;
import com.example.buslogic.model.Question;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin/question")
public class AdminQuestionController {

    final AdminQuestionService questionService;

    public AdminQuestionController(AdminQuestionService adminQuestionService) {
        this.questionService = adminQuestionService;
    }

    @GetMapping(path = "/approveList")
    public List<Question> getAllToApp() {
        return questionService.getListToApprove();
    }

    @PutMapping(path = "/{id}/approve")
    public void approveQuestion(@PathVariable Long id) {
        questionService.approve(id);
    }

    @PutMapping(path = "/{id}/decline")
    public void deleteQuestion(@PathVariable Long id) {
        questionService.decline(id);
    }


}
