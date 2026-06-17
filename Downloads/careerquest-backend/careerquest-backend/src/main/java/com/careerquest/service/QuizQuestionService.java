package com.careerquest.service;

import com.careerquest.dto.*;

import java.util.List;

public interface QuizQuestionService {
    QuizQuestionsDto createQuestion(
            QuizQuestionsDto questionDto);

    List<QuizQuestionsDto> getAllQuestions();
    CareerRecommendationDto submitQuiz(
            QuizSubmissionDto submissionDto);
    List<QuizResultDto> getQuizHistory(
            String userEmail);
    ParentDashboardDto getDashboard(String email);
}
