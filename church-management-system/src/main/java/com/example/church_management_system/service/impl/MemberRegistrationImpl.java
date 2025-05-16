package com.example.church_management_system.service.impl;

import com.example.church_management_system.Models.MemberRegistration;
import com.example.church_management_system.Dto.MemberRegistrationDto;
import com.example.church_management_system.repository.MemberRegistrationRepository;
import com.example.church_management_system.service.MemberRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberRegistrationImpl implements MemberRegistrationService {

    private final MemberRegistrationRepository memberRegistrationRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberRegistrationImpl(MemberRegistrationRepository memberRegistrationRepository, PasswordEncoder passwordEncoder) {
        this.memberRegistrationRepository = memberRegistrationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<MemberRegistrationDto> findAllMemberRegistrations() {
        return memberRegistrationRepository.findAll()
                .stream()
                .map(this::mapToMemberRegistrationDto)
                .collect(Collectors.toList());
    }

    @Override
    public MemberRegistrationDto mapToMemberRegistrationDto(MemberRegistration memberRegistration) {
        return MemberRegistrationDto.builder()
                .memberId(String.valueOf(memberRegistration.getId()))
                .fullName(memberRegistration.getFullName())
                .email(memberRegistration.getEmail())
                .phone(memberRegistration.getPhone())
                .dob(memberRegistration.getDob())
                .address(memberRegistration.getAddress())
                .dateJoined(memberRegistration.getDateJoined())
                .password(memberRegistration.getPassword())
                .build();
    }

    @Override
    public MemberRegistrationDto createMember(MemberRegistrationDto memberDto) {
        validatePasswords(memberDto);

        // Just return the DTO after validation; no saving
        return MemberRegistrationDto.builder()
                .fullName(memberDto.getFullName())
                .email(memberDto.getEmail())
                .phone(memberDto.getPhone())
                .dob(memberDto.getDob())
                .address(memberDto.getAddress())
                .dateJoined(memberDto.getDateJoined()) // Keep as provided from controller
                .password(passwordEncoder.encode(memberDto.getPassword())) // Encode here if needed
                .confirmPassword(memberDto.getConfirmPassword())
                .build();
    }

    @Override
    public MemberRegistrationDto updateMember(Long id, MemberRegistrationDto memberDto) {
        validatePasswords(memberDto);

        Optional<MemberRegistration> optionalMember = memberRegistrationRepository.findById(id);
        if (optionalMember.isPresent()) {
            MemberRegistration member = optionalMember.get();
            member.setFullName(memberDto.getFullName());
            member.setEmail(memberDto.getEmail());
            member.setPhone(memberDto.getPhone());
            member.setDob(memberDto.getDob());
            member.setAddress(memberDto.getAddress());
            if (memberDto.getPassword() != null && !memberDto.getPassword().isEmpty()) {
                member.setPassword(passwordEncoder.encode(memberDto.getPassword()));
            }
            member = memberRegistrationRepository.save(member);
            return mapToMemberRegistrationDto(member);
        } else {
            throw new RuntimeException("Member not found with ID: " + id);
        }
    }

    @Override
    public void deleteMember(Long id) {
        if (memberRegistrationRepository.existsById(id)) {
            memberRegistrationRepository.deleteById(id);
        } else {
            throw new RuntimeException("Member not found with ID: " + id);
        }
    }

    private void validatePasswords(MemberRegistrationDto memberDto) {
        if (memberDto.getPassword() == null || memberDto.getConfirmPassword() == null) {
            throw new IllegalArgumentException("Password and Confirm Password must not be null");
        }
        if (!memberDto.getPassword().equals(memberDto.getConfirmPassword())) {
            throw new IllegalArgumentException("Password and Confirm Password do not match");
        }
    }
}