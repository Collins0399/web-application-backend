package com.example.church_management_system.controller;

import com.example.church_management_system.Models.Admin;
import com.example.church_management_system.service.AdminAuthService;
import com.example.church_management_system.service.impl.AdminAuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private AdminAuthService adminAuthService;

    @PostMapping("/admin/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        boolean isAuthenticated = adminAuthService.authenticate(username, password);
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
    @PostMapping("/createAdmin")
    public ResponseEntity<Admin> createAdmin(@RequestParam String username, @RequestParam String password) {
        try {
            Admin admin = ((AdminAuthServiceImpl) adminAuthService).createAdmin(username, password);
            return ResponseEntity.ok(admin);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
