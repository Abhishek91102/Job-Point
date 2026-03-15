package com.secuser.jobportal.repository;

import com.secuser.jobportal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Use this to find a user by their unique email
    Optional<User> findByEmail(String email);

    // This checks if an email is already taken during signup
    boolean existsByEmail(String email);

    // DELETE or COMMENT OUT any method named 'findByUser'
    // as it is causing the "No property found" crash.
}