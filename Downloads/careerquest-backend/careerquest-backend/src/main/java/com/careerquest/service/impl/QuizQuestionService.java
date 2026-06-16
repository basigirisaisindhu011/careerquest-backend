package com.careerquest.service.impl;

import com.careerquest.dto.CareerRecommendationDto;
import com.careerquest.dto.QuizQuestionsDto;
import com.careerquest.dto.QuizResultDto;
import com.careerquest.dto.QuizSubmissionDto;

import java.util.List;

public interface QuizQuestionService {
    QuizQuestionsDto createQuestion(
            QuizQuestionsDto questionDto);

    List<QuizQuestionsDto> getAllQuestions();
    CareerRecommendationDto submitQuiz(
            QuizSubmissionDto submissionDto);
    List<QuizResultDto> getQuizHistory(
            String userEmail);
}
