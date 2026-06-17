package com.careerquest.service;

import com.careerquest.dto.CareerDto;
import com.careerquest.entity.Career;
import com.careerquest.repository.CareerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CareerServiceImpl implements CareerService {

    private final CareerRepository careerRepository;

    @Override
    public CareerDto createCareer(CareerDto careerDto) {

        Career career = mapToEntity(careerDto);

        Career savedCareer = careerRepository.save(career);

        return mapToDto(savedCareer);
    }

    @Override
    public List<CareerDto> getAllCareers() {

        return careerRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CareerDto getCareerById(Long id) {

        Career career = careerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Career not found"));

        return mapToDto(career);
    }

    @Override
    public CareerDto updateCareer(Long id, CareerDto careerDto) {

        Career career = careerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Career not found"));

        career.setName(careerDto.getName());
        career.setDescription(careerDto.getDescription());
        career.setSkills(careerDto.getSkills());
        career.setDailyWork(careerDto.getDailyWork());
        career.setSalaryRange(careerDto.getSalaryRange());
        career.setEducationPath(careerDto.getEducationPath());
        career.setImageUrl(careerDto.getImageUrl());

        Career updatedCareer = careerRepository.save(career);

        return mapToDto(updatedCareer);
    }

    @Override
    public void deleteCareer(Long id) {

        Career career = careerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Career not found"));

        careerRepository.delete(career);
    }

    private CareerDto mapToDto(Career career) {

        return CareerDto.builder()
                .id(career.getId())
                .name(career.getName())
                .description(career.getDescription())
                .skills(career.getSkills())
                .dailyWork(career.getDailyWork())
                .salaryRange(career.getSalaryRange())
                .educationPath(career.getEducationPath())
                .imageUrl(career.getImageUrl())
                .build();
    }

    private Career mapToEntity(CareerDto dto) {

        return Career.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .skills(dto.getSkills())
                .dailyWork(dto.getDailyWork())
                .salaryRange(dto.getSalaryRange())
                .educationPath(dto.getEducationPath())
                .imageUrl(dto.getImageUrl())
                .build();
    }
}