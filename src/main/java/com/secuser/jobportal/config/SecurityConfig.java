package com.secuser.jobportal.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. MUST DISABLE CSRF for REST APIs / Postman
                .csrf(csrf -> csrf.disable())

                // 2. Set Session to Stateless (Standard for JWT/REST)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // 3. Configure Path Permissions
                .authorizeHttpRequests(auth -> auth
                        // Make sure the leading slash "/" is present
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/error").permitAll() // Helps debug internal errors
                        .anyRequest().authenticated()
                );

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}