package com.example.buslogic.admin.controller;

import com.example.buslogic.admin.service.AdminQuestionService;
import com.example.buslogic.model.Question;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin")
public class AdminQuestionController {

    final AdminQuestionService adminQuestionService;

    public AdminQuestionController(AdminQuestionService adminQuestionService) {
        this.adminQuestionService = adminQuestionService;
    }

    @GetMapping(path = "/app")
    public List<Question> getAllToApp() {
        return adminQuestionService.getListToApprove();
    }

    @PutMapping(path = "/{id}")
    public void approveQuestion(@PathVariable Long id) {
        adminQuestionService.approveQuestion(id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        adminQuestionService.deleteQuestion(id);
    }


}
