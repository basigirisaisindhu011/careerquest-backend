package com.careerquest.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CareerDto {

    private Long id;
    private String name;
    private String description;
    private String skills;
    private String dailyWork;
    private String salaryRange;
    private String educationPath;
    private String imageUrl;
}