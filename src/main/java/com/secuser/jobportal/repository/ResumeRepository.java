package com.secuser.jobportal.repository;

import com.secuser.jobportal.entity.Resume;
import com.secuser.jobportal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    List<Resume> findByUser(User user);
}