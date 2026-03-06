package com.secuser.jobportal.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {

    // Must be at least 32 characters long
    private final String jwtSecret = "jdfjjfnruh954t54nfrjfurbhb4grjgrgnrig5th4tuitu45gbrgggrogu45tto94t4ut54tu94g4f5f885b54f48t89g58t5r4rfbru47t58o";
    private final int jwtExpirationMs = 86400000; // 24 hours

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email) // FIXED: Changed to setSubject for better compatibility
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(getSigningKey())
                .compact();
    }
}