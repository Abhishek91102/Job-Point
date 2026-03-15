package com.secuser.jobportal.controller;

import com.secuser.jobportal.entity.MasterProfile;
import com.secuser.jobportal.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    // Save or Update the profile for the logged-in user
    @PostMapping("/save")
    public ResponseEntity<MasterProfile> saveProfile(@RequestBody MasterProfile profile, Principal principal) {
        // principal.getName() is the email extracted from the JWT
        String email = principal.getName();
        return ResponseEntity.ok(profileService.saveOrUpdateProfile(email, profile));
    }

    // Get the current logged-in user's profile
    @GetMapping("/me")
    public ResponseEntity<MasterProfile> getMyProfile(Principal principal) {
        String email = principal.getName();
        return ResponseEntity.ok(profileService.getProfileByEmail(email));
    }
}