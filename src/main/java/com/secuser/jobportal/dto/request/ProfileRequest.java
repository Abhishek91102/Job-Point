package com.secuser.jobportal.dto.request;

public record ProfileRequest(
        String phoneNumber,
        String linkedinUrl,
        String githubUrl,
        String portfolioUrl,
        String summary,
        String education,  // JSON String
        String experience, // JSON String
        String skills,     // JSON String
        boolean requiresSponsorship,
        String currentCity,
        String noticePeriod
) {}