package com.example.church_management_system.service.impl;

import com.example.church_management_system.Models.Admin;
import com.example.church_management_system.repository.AdminRepository;
import com.example.church_management_system.service.AdminAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminAuthServiceImpl implements AdminAuthService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean authenticate(String username , String password) {
        Admin admin = adminRepository.findByUsername(username);
        if (admin != null) {
            return  admin.getPassword().equals(password);
        }
        return false;
    }
    public Admin createAdmin(String username, String password) {
        if (adminRepository.findByUsername(username) != null) {
            throw new IllegalArgumentException("Admin with this username already exists");
        }
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(passwordEncoder.encode(password));
        return adminRepository.save(admin);
    }
}
