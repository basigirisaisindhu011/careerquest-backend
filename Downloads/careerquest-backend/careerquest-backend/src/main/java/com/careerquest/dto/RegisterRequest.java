package com.careerquest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotBlank(message="Name is required")
    private String name;
    @NotBlank(message = "Email is required")
    @Email(message="Invalid email")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;
    @NotBlank(message="Role is required")
    private String role;
}
