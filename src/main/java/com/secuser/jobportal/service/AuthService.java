package com.secuser.jobportal.service;

import com.secuser.jobportal.config.JwtUtils; // Ensure this import exists
import com.secuser.jobportal.dto.request.LoginRequest;
import com.secuser.jobportal.dto.request.SignupRequest;
import com.secuser.jobportal.dto.response.AuthResponse;
import com.secuser.jobportal.dto.response.UserResponse;
import com.secuser.jobportal.entity.User;
import com.secuser.jobportal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils; // FIXED: Added this field for injection

    public UserResponse registerUser(SignupRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Error: Email is already in use!");
        }

        User user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();

        User savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getEmail(),
                savedUser.getCreatedAt()
        );
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("User not found!"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials!");
        }

        // Now jwtUtils is recognized and usable
        String token = jwtUtils.generateToken(user.getEmail());

        return new AuthResponse(token, user.getEmail(), user.getFirstName());
    }
}