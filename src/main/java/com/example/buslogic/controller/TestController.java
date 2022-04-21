package com.example.buslogic.controller;

import com.example.buslogic.activeMQ.MessageSender;
import com.example.buslogic.service.QuestionService;
import com.example.buslogic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "test")
public class TestController {

    @Autowired
    UserService userService;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    MessageSender messageSender;

    @Autowired
    QuestionService questionService;

    @GetMapping()
    public String getTest() {

        questionService.setQuestionTimeout();

        return "test";
    }

    @GetMapping(path = "/{str}")
    public void testJMS(@PathVariable String str){
    }
}
