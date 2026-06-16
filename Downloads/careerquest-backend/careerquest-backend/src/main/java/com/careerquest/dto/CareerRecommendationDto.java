package com.careerquest.dto;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CareerRecommendationDto {

    private String topCareer;

    private Integer matchPercentage;

    private Map<String,Integer> scores;
}