package com.careerquest.repository;

import com.careerquest.entity.Career;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareerRepository extends JpaRepository<Career,Long> {
}
