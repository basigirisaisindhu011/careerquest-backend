package com.careerquest.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizQuestionsDto {

    private Long id;

    private String question;

    private String optionA;

    private String optionB;

    private String optionC;

    private String careerA;

    private String careerB;

    private String careerC;
}