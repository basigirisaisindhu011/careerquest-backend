package com.careerquest.service;

import com.careerquest.dto.*;
import com.careerquest.entity.QuizResult;
import com.careerquest.repository.QuizResultRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.careerquest.entity.QuizQuestion;
import com.careerquest.repository.QuizQuestionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizQuestionServiceImpl implements QuizQuestionService {

    private final QuizQuestionRepository quizQuestionRepository;
    private final QuizResultRepository quizResultRepository;
    @Override
    public QuizQuestionsDto createQuestion(QuizQuestionsDto questionDto) {

        QuizQuestion question = mapToEntity(questionDto);

        QuizQuestion savedQuestion =
                quizQuestionRepository.save(question);

        return mapToDto(savedQuestion);
    }

    @Override
    public List<QuizQuestionsDto> getAllQuestions() {

        return quizQuestionRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private QuizQuestionsDto mapToDto(
            QuizQuestion question) {

        return QuizQuestionsDto.builder()
                .id(question.getId())
                .question(question.getQuestion())
                .optionA(question.getOptionA())
                .optionB(question.getOptionB())
                .optionC(question.getOptionC())
                .careerA(question.getCareerA())
                .careerB(question.getCareerB())
                .careerC(question.getCareerC())
                .build();
    }

    private QuizQuestion mapToEntity(
            QuizQuestionsDto dto) {

        return QuizQuestion.builder()
                .id(dto.getId())
                .question(dto.getQuestion())
                .optionA(dto.getOptionA())
                .optionB(dto.getOptionB())
                .optionC(dto.getOptionC())
                .careerA(dto.getCareerA())
                .careerB(dto.getCareerB())
                .careerC(dto.getCareerC())
                .build();
    }
    @Override
    public CareerRecommendationDto submitQuiz(
            QuizSubmissionDto submissionDto) {

        Map<String, Integer> scores =
                new HashMap<>();

        for (String career :
                submissionDto.getSelectedCareers()) {

            scores.put(
                    career,
                    scores.getOrDefault(career, 0) + 1
            );
        }

        String topCareer =
                scores.entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .get()
                        .getKey();

        int highestScore =
                scores.get(topCareer);

        int totalQuestions =
                submissionDto.getSelectedCareers().size();

        int percentage =
                (highestScore * 100)
                        / totalQuestions;

        QuizResult quizResult =
                QuizResult.builder()
                        .userEmail(
                                submissionDto.getUserEmail())
                        .topCareer(topCareer)
                        .matchPercentage(percentage)
                        .createdAt(LocalDateTime.now())
                        .build();

        quizResultRepository.save(quizResult);

        return CareerRecommendationDto.builder()
                .topCareer(topCareer)
                .matchPercentage(percentage)
                .scores(scores)
                .build();
    }
    @Override
    public List<QuizResultDto> getQuizHistory(
            String userEmail) {

        return quizResultRepository
                .findByUserEmail(userEmail)
                .stream()
                .map(result ->
                        QuizResultDto.builder()
                                .id(result.getId())
                                .userEmail(result.getUserEmail())
                                .topCareer(result.getTopCareer())
                                .matchPercentage(
                                        result.getMatchPercentage())
                                .createdAt(result.getCreatedAt())
                                .build())
                .toList();
    }
    @Override
    public ParentDashboardDto getDashboard(String email) {

        List<QuizResult> results =
                quizResultRepository.findByUserEmail(email);

        if(results.isEmpty()) {
            throw new RuntimeException("No quiz results found");
        }

        long totalQuizzes = results.size();

        double averagePercentage =
                results.stream()
                        .mapToInt(QuizResult::getMatchPercentage)
                        .average()
                        .orElse(0);

        String topCareer =
                results.stream()
                        .collect(Collectors.groupingBy(
                                QuizResult::getTopCareer,
                                Collectors.counting()))
                        .entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .get()
                        .getKey();

        return ParentDashboardDto.builder()
                .childEmail(email)
                .totalQuizzes(totalQuizzes)
                .topCareer(topCareer)
                .averageMatchPercentage(averagePercentage)
                .build();
    }
}