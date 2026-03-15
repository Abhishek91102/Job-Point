package com.secuser.jobportal.service;

import com.secuser.jobportal.entity.MasterProfile;
import com.secuser.jobportal.entity.User;
import com.secuser.jobportal.repository.MasterProfileRepository;
import com.secuser.jobportal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final MasterProfileRepository profileRepository;
    private final UserRepository userRepository;

    /**
     * Saves or updates the Master Profile for a specific user.
     * Extracts the user from the database using the email provided in the JWT.
     */
    public MasterProfile saveOrUpdateProfile(String email, MasterProfile profileData) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if a profile already exists for this user
        MasterProfile profile = profileRepository.findByUser(user)
                .orElse(new MasterProfile());

        profile.setUser(user);
        profile.setPhoneNumber(profileData.getPhoneNumber());
        profile.setLinkedinUrl(profileData.getLinkedinUrl());
        profile.setGithubUrl(profileData.getGithubUrl());
        profile.setPortfolioUrl(profileData.getPortfolioUrl());
        profile.setSummary(profileData.getSummary());
        profile.setEducation(profileData.getEducation());
        profile.setExperience(profileData.getExperience());
        profile.setSkills(profileData.getSkills());
        profile.setRequiresSponsorship(profileData.isRequiresSponsorship());
        profile.setCurrentCity(profileData.getCurrentCity());
        profile.setNoticePeriod(profileData.getNoticePeriod());
        profile.setExpectedSalary(profileData.getExpectedSalary());

        return profileRepository.save(profile);
    }

    /**
     * Retrieves the profile associated with the logged-in user.
     */
    public MasterProfile getProfileByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return profileRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Profile not found for this user"));
    }
}