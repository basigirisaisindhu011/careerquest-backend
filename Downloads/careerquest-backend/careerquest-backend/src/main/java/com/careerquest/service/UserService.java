package com.careerquest.service;

import com.careerquest.dto.LoginRequest;
import com.careerquest.dto.LoginResponse;
import com.careerquest.dto.RegisterRequest;

public interface UserService {
    String registerUser(RegisterRequest request);
    LoginResponse loginUser(LoginRequest request);
}
