package com.careerquest.controller;

import com.careerquest.dto.LoginRequest;
import com.careerquest.dto.LoginResponse;
import com.careerquest.dto.RegisterRequest;
import com.careerquest.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public String registerUser(@Valid @RequestBody RegisterRequest request){
        return userService.registerUser(request);
    }
    @PostMapping("/login")
    public LoginResponse loginUser(@Valid @RequestBody LoginRequest request){
        return userService.loginUser(request);
    }

}
