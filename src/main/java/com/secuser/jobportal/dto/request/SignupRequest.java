package com.secuser.jobportal.dto.request;


import jakarta.validation.constraints.*;

/**
 * DTO for secure user registration.
 * Includes validation to ensure no 'lazy' or empty data enters our system.
 */
public record SignupRequest(
        @NotBlank(message = "First name is required")
        String firstName,

        @NotBlank(message = "Last name is required")
        String lastName,

        @NotBlank(message = "Email is required")
        @Email(message = "Please provide a valid email address")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password
) {}