package com.secuser.jobportal.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "master_profiles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MasterProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String phoneNumber;
    private String linkedinUrl;
    private String githubUrl;
    private String portfolioUrl;

    @Column(columnDefinition = "TEXT")
    private String summary;

    @Column(columnDefinition = "TEXT")
    private String education; // JSON String: schools, degrees, dates

    @Column(columnDefinition = "TEXT")
    private String experience; // JSON String: roles, companies, achievements

    @Column(columnDefinition = "TEXT")
    private String skills; // JSON String: tech stack, soft skills

    // Fields for "Zero-Failure" AI automation
    private boolean requiresSponsorship;
    private String currentCity;
    private String noticePeriod;
    private String expectedSalary;
}