package com.careerquest.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "careers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 3000)
    private String description;

    @Column(length = 2000)
    private String skills;

    @Column(length = 2000)
    private String dailyWork;

    private String salaryRange;

    @Column(length = 2000)
    private String educationPath;

    private String imageUrl;
}