package com.careerquest.controller;


import com.careerquest.dto.*;
import com.careerquest.service.QuizQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor
@CrossOrigin("*")
public class QuizQuestionController {

    private final QuizQuestionService quizQuestionService;

    @PostMapping
    public QuizQuestionsDto createQuestion(
            @RequestBody QuizQuestionsDto dto) {

        return quizQuestionService.createQuestion(dto);
    }
    @PostMapping("/submit")
    public CareerRecommendationDto submitQuiz(
            @RequestBody QuizSubmissionDto dto) {

        return quizQuestionService.submitQuiz(dto);
    }

    @GetMapping
    public List<QuizQuestionsDto> getAllQuestions() {

        return quizQuestionService.getAllQuestions();
    }
    @GetMapping("/results/{email}")
    public List<QuizResultDto> getQuizHistory(
            @PathVariable String email) {

        return quizQuestionService
                .getQuizHistory(email);
    }

    @GetMapping("/dashboard/{email}")
    public ParentDashboardDto getDashboard(
            @PathVariable String email) {

        return quizQuestionService
                .getDashboard(email);
    }
}