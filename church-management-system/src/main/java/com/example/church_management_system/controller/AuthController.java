package com.example.church_management_system.controller;

import com.example.church_management_system.Dto.Auth.LoginDto;
import com.example.church_management_system.Models.Admin;
import com.example.church_management_system.config.KeyEncoder;
import com.example.church_management_system.repository.AdminRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.security.Key;

import java.util.Date;
import java.util.Optional;
import java.util.Base64;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final AdminRepository adminRepository;


    @Autowired
    public AuthController(PasswordEncoder passwordEncoder, AdminRepository adminRepository) {
        this.passwordEncoder = passwordEncoder;
        this.adminRepository = adminRepository;
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        try {
            String email = loginDto.getEmail();
            String password = loginDto.getPassword();

            if (email == null || email.isEmpty()) {
                return ResponseEntity.badRequest().body(new ErrorResponse("Email is required"));
            }

            Optional<Admin> admin = adminRepository.findByEmail(email);
            if (admin.isPresent() && passwordEncoder.matches(password, admin.get().getPassword())) {
                String token = generateToken(email);
                LoginResponse loginResponse = new LoginResponse("Login successful", "admin", true, token);
                return ResponseEntity.ok(loginResponse);
            }

            return ResponseEntity.badRequest().body(new ErrorResponse("Invalid email or password"));
        } catch (Exception e) {
            e.printStackTrace(); // Log the error for debugging
            return ResponseEntity.status(500).body(new ErrorResponse("An error occurred"));
        }
    }

    private String generateToken(String email) {
        Key secretKey = KeyEncoder.getSecretKey();

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day expiration
                .signWith(secretKey) // Use the Key object
                .compact();
    }

    static class ErrorResponse {
        private String error;
        private boolean success;

        public ErrorResponse(String error) {
            this.error = error;
            this.success = false;
        }

        public String getError() {
            return error;
        }

        public boolean isSuccess() {
            return success;
        }
    }

    static class LoginResponse {
        private String message;
        private String role;
        private boolean success;
        private String token;

        public LoginResponse(String message, String role, boolean success, String token) {
            this.message = message;
            this.role = role;
            this.success = success;
            this.token = token;
        }

        public String getMessage() {
            return message;
        }

        public String getRole() {
            return role;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getToken() {
            return token;
        }
    }
}