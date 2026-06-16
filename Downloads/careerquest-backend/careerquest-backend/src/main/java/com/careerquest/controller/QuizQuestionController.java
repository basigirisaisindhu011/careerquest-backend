package com.careerquest.controller;


import com.careerquest.dto.CareerRecommendationDto;
import com.careerquest.dto.QuizQuestionsDto;
import com.careerquest.dto.QuizResultDto;
import com.careerquest.dto.QuizSubmissionDto;
import com.careerquest.service.impl.QuizQuestionService;
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
}