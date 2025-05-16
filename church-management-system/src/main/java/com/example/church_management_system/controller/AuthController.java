package com.example.church_management_system.controller;

import com.example.church_management_system.Dto.Auth.LoginDto;
import com.example.church_management_system.Models.MemberRegistration;
import com.example.church_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.church_management_system.Models.Admin;
import com.example.church_management_system.repository.AdminRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AdminRepository adminRepository;
    private final String ADMIN_ROLE = "ADMIN";


    @Autowired
    public AuthController(UserService userService, PasswordEncoder passwordEncoder , AdminRepository adminRepository) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.adminRepository = adminRepository;
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        try {
            // Use email directly for login
            String email = loginDto.getEmail();

            // Check if email is null
            if (email == null) {
                System.out.println("Email is null");
                return ResponseEntity.badRequest().body(new ErrorResponse("Email is required"));
            }

// Check if the user is an admin
            Optional<Admin> admin = adminRepository.findByEmail(email);
            if (admin.isPresent() && passwordEncoder.matches(loginDto.getPassword(), admin.get().getPassword())) {
                return ResponseEntity.ok(new LoginResponse("Login successful", "admin"));
            }

// Check if the user is a member
            Optional<MemberRegistration> member = userService.findUserByEmail(email);
            if (member.isPresent() && passwordEncoder.matches(loginDto.getPassword(), member.get().getPassword())) {
                return ResponseEntity.ok(new LoginResponse("Login successful", "member"));
            }


            return ResponseEntity.badRequest().body(new ErrorResponse("Invalid email or password"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("An error occurred"));
        }
    }

    // Response classes for consistent JSON responses
    static class SuccessResponse {
        private String message;

        public SuccessResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    static class ErrorResponse {
        private String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }
    }
    static class LoginResponse {
        private String message;
        private String role;
        private Long Id;

        public LoginResponse(String message, String role) {
            this.message = message;
            this.role = role;
            this.Id = Id;
        }

        public String getMessage() {
            return message;
        }

        public String getRole() {
            return role;
        }
        public Long getId(){
            return Id;
        }
    }
}