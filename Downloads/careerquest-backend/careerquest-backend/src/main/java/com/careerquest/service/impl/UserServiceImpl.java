package com.careerquest.service.impl;

import com.careerquest.dto.LoginRequest;
import com.careerquest.dto.LoginResponse;
import com.careerquest.dto.RegisterRequest;
import com.careerquest.entity.User;
import com.careerquest.repository.UserRepository;
import com.careerquest.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String registerUser(RegisterRequest request){
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // Encryption later
                .role(request.getRole())
                .build();

        userRepository.save(user);

        return "User Registered Successfully";
    }
    @Override
    public LoginResponse loginUser(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        boolean matches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!matches) {
            throw new RuntimeException("Invalid Password");
        }

        String token =
                JwtUtil.generateToken(
                        user.getEmail());

        return LoginResponse.builder()
                .message("Login Successful")
                .email(user.getEmail())
                .role(user.getRole())
                .token(token)
                .build();
    }
}
