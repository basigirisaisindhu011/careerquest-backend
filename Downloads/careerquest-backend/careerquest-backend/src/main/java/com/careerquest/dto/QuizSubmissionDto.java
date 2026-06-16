package com.careerquest.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizSubmissionDto {

    private String userEmail;

    private List<String> selectedCareers;
}