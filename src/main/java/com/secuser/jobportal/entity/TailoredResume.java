package com.secuser.jobportal.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tailored_resumes")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TailoredResume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String jobTitle;
    private String companyName;

    @Column(columnDefinition = "TEXT")
    private String originalContent; // Text extracted from your original resume

    @Column(columnDefinition = "TEXT")
    private String tailoredContent; // The NEW text with the AI-added key points

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}