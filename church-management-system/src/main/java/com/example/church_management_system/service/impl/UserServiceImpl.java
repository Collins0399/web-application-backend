package com.example.church_management_system.service.impl;

import com.example.church_management_system.Models.MemberRegistration;
import com.example.church_management_system.repository.UserRepository;
import com.example.church_management_system.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<MemberRegistration> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public MemberRegistration saveUser(MemberRegistration user) {
        return userRepository.save(user);


    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}