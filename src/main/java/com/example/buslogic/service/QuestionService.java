package com.example.buslogic.service;

import com.example.buslogic.model.dto.QuestionDTO;
import com.example.buslogic.model.Question;
import com.example.buslogic.model.Status;
import com.example.buslogic.model.User;
import com.example.buslogic.service.repository.QuestionRepository;
import com.example.buslogic.security.BadWordsFilter;
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
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final UserService userService;

    BadWordsFilter badWordsFilter = new BadWordsFilter();

    @Autowired
    public QuestionService(QuestionRepository questionRepository, UserService userService) {
        this.questionRepository = questionRepository;
        this.userService = userService;
    }

    public List<Question> getUserQuestions() {
        User user = getUserFromContext();
        log.info("user {} get question", user.getUsername());
        return questionRepository.getAllByUserId(user.getId());
    }

    @Transactional
    public Question createQuestion(QuestionDTO dto) {
        Question question = new Question();
        User user = getUserFromContext();

        question.setContent(dto.getContent());
        question.setTitle(dto.getTitle());
        question.setUserId(user.getId());

        if (badWordsFilter.check(dto.getTitle(), dto.getContent())) {
            question.setStatus(Status.APPROVED);
        } else {
            question.setStatus(Status.ON_MODERATION);
            log.warn("Detected warning question from user #{}", user.getUsername());
        }

        log.info("user {} created question", user.getUsername());
        return questionRepository.save(question);
    }

    public void deleteQuestion(Long id) throws AccessDeniedException {
        User user = getUserFromContext();
        Question question = questionRepository.getById(id);

        if (!user.getId().equals(question.getUserId())) {
            log.warn("ERROR. user {} wanted delete question #{}", user.getUsername(), question.getId());
            throw new AccessDeniedException("Доступ ограничен");
        }

        questionRepository.deleteById(question.getId());
        log.info("user {} deleted question #{}", user.getUsername(), question.getId());
    }

    private User getUserFromContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        return userService.findByUsername(currentUser);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.getAllQuestions();
    }
}
