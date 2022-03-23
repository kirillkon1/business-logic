package com.example.buslogic.controller;

import com.example.buslogic.model.dto.VoteDto;
import com.example.buslogic.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "vote")
public class VoteController {

    @Autowired
    VoteService voteService;

    @PutMapping
    public void vote(@RequestBody VoteDto voteDto){
        voteService.createVote(voteDto);
    }
}
