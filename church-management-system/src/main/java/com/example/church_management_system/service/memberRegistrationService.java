package com.example.church_management_system.service;

import com.example.church_management_system.Models.memberRegistration;
import com.example.church_management_system.Dto.memberRegistrationDto;

import java.util.List;

public interface memberRegistrationService {
    List<memberRegistrationDto> findAllmemberRegistration();

    memberRegistrationDto mapToMemberRegistrationDto(memberRegistration memberRegistration);

    memberRegistrationDto createMember(memberRegistrationDto memberDto);

    memberRegistrationDto updateMember(Long id, memberRegistrationDto memberDto);

    void deleteMember(Long id);
}