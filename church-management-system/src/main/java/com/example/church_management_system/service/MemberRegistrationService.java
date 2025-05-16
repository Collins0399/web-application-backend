package com.example.church_management_system.service;

import com.example.church_management_system.Dto.MemberRegistrationDto;
import java.util.List;

public interface MemberRegistrationService {
    List<MemberRegistrationDto> findAllMemberRegistrations();
    MemberRegistrationDto createMember(MemberRegistrationDto memberDto);
    MemberRegistrationDto updateMember(Long id, MemberRegistrationDto memberDto);
    void deleteMember(Long id);
    MemberRegistrationDto mapToMemberRegistrationDto(com.example.church_management_system.Models.MemberRegistration memberRegistration);
}