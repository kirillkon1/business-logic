package com.example.buslogic.service;

import com.example.buslogic.model.Answer;
import com.example.buslogic.model.Status;
import com.example.buslogic.model.User;
import com.example.buslogic.model.dto.AnswerDTO;
import com.example.buslogic.security.BadWordsFilter;
import com.example.buslogic.service.repository.AnswerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
@Slf4j
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final UserService userService;

    private final BadWordsFilter badWordsFilter = new BadWordsFilter();

    @Autowired
    public AnswerService(AnswerRepository answerRepository, UserService userService) {
        this.answerRepository = answerRepository;
        this.userService = userService;
    }

    public List<Answer> getAnswersByQuestion(Long id) {
        User user = getUserFromContext();
        log.info("user {} get question", user.getUsername());
        return answerRepository.getAllByQuestionId(id);
    }

    @Transactional
    public Answer createAnswer(AnswerDTO dto) {
        Answer answer = new Answer();
        User user = getUserFromContext();

        answer.setContent(dto.getContent());
        answer.setUserId(user.getId());
        answer.setQuestionId(dto.getQuestion_id());
        answer.setRating(0L);

        if (badWordsFilter.check(dto.getContent(), "")) {
            answer.setStatus(Status.APPROVED);
        } else {
            answer.setStatus(Status.ON_MODERATION);
            log.warn("Detected warning answer from user #{} in question id #{}", user.getUsername(), dto.getQuestion_id());
        }

//        log.info("user {} created question", user.getUsername());
        return answerRepository.save(answer);
    }

    public void deleteAnswer(Long id) throws AccessDeniedException {
        User user = getUserFromContext();
        Answer answer = answerRepository.getById(id);

        if (!user.getId().equals(answer.getUserId())) {
            log.warn("ERROR. user {} wanted delete question #{}", user.getUsername(), answer.getId());
            throw new AccessDeniedException("Доступ ограничен");
        }

        answerRepository.deleteById(answer.getId());
    }

    private User getUserFromContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        return userService.findByUsername(currentUser);
    }
}
