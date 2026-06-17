package com.careerquest.service;

import com.careerquest.dto.CareerDto;

import java.util.List;

public interface CareerService {
    CareerDto createCareer(CareerDto careerDto);

    List<CareerDto> getAllCareers();

    CareerDto getCareerById(Long id);

    CareerDto updateCareer(Long id, CareerDto careerDto);

    void deleteCareer(Long id);
}
