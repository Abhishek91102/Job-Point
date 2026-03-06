package com.secuser.jobportal.dto.response;

public record AuthResponse(
        String token,
        String email,
        String firstName
) {}