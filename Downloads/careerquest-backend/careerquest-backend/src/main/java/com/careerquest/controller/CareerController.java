package com.careerquest.controller;

import com.careerquest.dto.CareerDto;

import com.careerquest.service.impl.CareerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/careers")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CareerController {

    private final CareerService careerService;

    @PostMapping
    public CareerDto createCareer(
            @RequestBody CareerDto careerDto) {

        return careerService.createCareer(careerDto);
    }

    @GetMapping
    public List<CareerDto> getAllCareers() {

        return careerService.getAllCareers();
    }

    @GetMapping("/{id}")
    public CareerDto getCareerById(
            @PathVariable Long id) {

        return careerService.getCareerById(id);
    }

    @PutMapping("/{id}")
    public CareerDto updateCareer(
            @PathVariable Long id,
            @RequestBody CareerDto careerDto) {

        return careerService.updateCareer(id, careerDto);
    }

    @DeleteMapping("/{id}")
    public String deleteCareer(
            @PathVariable Long id) {

        careerService.deleteCareer(id);

        return "Career Deleted Successfully";
    }
}