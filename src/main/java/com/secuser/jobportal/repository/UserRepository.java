package com.secuser.jobportal.repository;


import com.secuser.jobportal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Crucial for Login: Finds the user by email to verify password
    Optional<User> findByEmail(String email);

    // Checks if email already exists during Signup
    boolean existsByEmail(String email);
}