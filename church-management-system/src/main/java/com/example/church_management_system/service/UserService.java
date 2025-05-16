package com.example.church_management_system.service;

import com.example.church_management_system.Models.MemberRegistration;

import java.util.Optional;

public interface UserService {
    Optional<MemberRegistration> findUserByEmail(String email);

    MemberRegistration saveUser(MemberRegistration user);

    boolean emailExists(String email);
}