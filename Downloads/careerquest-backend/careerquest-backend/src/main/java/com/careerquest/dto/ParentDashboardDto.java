package com.careerquest.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParentDashboardDto {
    private String childEmail;
    private Long totalQuizzes;
    private String topCareer;
    private Double averageMatchPercentage;
}
