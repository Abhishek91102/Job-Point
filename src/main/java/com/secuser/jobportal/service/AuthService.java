package com.secuser.jobportal.service;



import com.secuser.jobportal.dto.request.SignupRequest;
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

    public UserResponse registerUser(SignupRequest request) {
        // 1. Check if email exists
        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Error: Email is already in use!");
        }

        // 2. Create new user and hash password
        User user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();

        // 3. Save to database
        User savedUser = userRepository.save(user);

        // 4. Return safe response
        return new UserResponse(
                savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getEmail(),
                savedUser.getCreatedAt()
        );
    }
}
