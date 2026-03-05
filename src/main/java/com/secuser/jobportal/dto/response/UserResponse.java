package com.secuser.jobportal.dto.response;


import java.time.LocalDateTime;

/**
 * Safe data returned to the client after successful signup or login.
 */
public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        LocalDateTime createdAt
) {}
