package com.secuser.jobportal.repository;

import com.secuser.jobportal.entity.MasterProfile;
import com.secuser.jobportal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MasterProfileRepository extends JpaRepository<MasterProfile, Long> {
    // This method allows finding the profile belonging to a specific user
    Optional<MasterProfile> findByUser(User user);
}