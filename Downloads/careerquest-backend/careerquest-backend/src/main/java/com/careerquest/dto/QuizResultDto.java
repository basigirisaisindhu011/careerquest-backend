package com.careerquest.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizResultDto {

    private Long id;

    private String userEmail;

    private String topCareer;

    private Integer matchPercentage;

    private LocalDateTime createdAt;
}