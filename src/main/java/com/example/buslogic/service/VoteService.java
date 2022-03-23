package com.example.buslogic.service;

import com.example.buslogic.model.Answer;
import com.example.buslogic.model.User;
import com.example.buslogic.model.Vote;
import com.example.buslogic.model.dto.VoteDto;
import com.example.buslogic.service.repository.AnswerRepository;
import com.example.buslogic.service.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VoteService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private VoteRepository voteRepository;

    @Transactional()
    public void createVote(VoteDto voteDto){
        Vote vote = new Vote();
        vote.setAnswerId(voteDto.getAnswer_id());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(authentication.getName());

        vote.setUserId(user.getId());
        vote.setPositive(voteDto.getIs_positive());

        Answer answer = answerRepository.getById(voteDto.getAnswer_id());

        if(voteDto.getIs_positive()){
            answer.setRating(answer.getRating() + 1);
        }else{
            answer.setRating(answer.getRating() - 1);
        }

        voteRepository.save(vote);
    }



}
