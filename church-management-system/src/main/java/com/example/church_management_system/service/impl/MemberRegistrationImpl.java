package com.example.church_management_system.service.impl;

import com.example.church_management_system.Models.memberRegistration;
import com.example.church_management_system.Dto.memberRegistrationDto;
import com.example.church_management_system.repository.memberRegistrationRepository;
import com.example.church_management_system.service.memberRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberRegistrationImpl implements memberRegistrationService {

    private final memberRegistrationRepository memberRegistrationRepository;

    @Autowired
    public MemberRegistrationImpl(memberRegistrationRepository memberRegistrationRepository) {
        this.memberRegistrationRepository = memberRegistrationRepository;
    }

    @Override
    public List<memberRegistrationDto> findAllmemberRegistration() {
        return memberRegistrationRepository.findAll()
                .stream()
                .map(this::mapToMemberRegistrationDto)
                .collect(Collectors.toList());
    }

    @Override
    public memberRegistrationDto mapToMemberRegistrationDto(memberRegistration memberRegistration) {
        return memberRegistrationDto.builder()
                .memberId(String.valueOf(memberRegistration.getId()))
                .fullName(memberRegistration.getFullName())
                .email(memberRegistration.getEmail())
                .phone(memberRegistration.getPhone())
                .DOB (memberRegistration.getDOB())
                .address(memberRegistration.getAddress())
                .dateJoined(memberRegistration.getDateJoined())
                .build();
    }

    @Override
    public memberRegistrationDto createMember(memberRegistrationDto memberDto) {
        memberRegistration member = new memberRegistration();
        member.setFullName(memberDto.getFullName());
        member.setEmail(memberDto.getEmail());
        member.setPhone(memberDto.getPhone());
        member.setDOB(memberDto.getDOB());
        member.setAddress(memberDto.getAddress());
        member.setDateJoined(LocalDate.now());
        member = memberRegistrationRepository.save(member);
        return mapToMemberRegistrationDto(member);
    }

    @Override
    public memberRegistrationDto updateMember(Long id, memberRegistrationDto memberDto) {
        Optional<memberRegistration> optionalMember = memberRegistrationRepository.findById(id);
        if (optionalMember.isPresent()) {
            memberRegistration member = optionalMember.get();
            member.setFullName(memberDto.getFullName());
            member.setEmail(memberDto.getEmail());
            member.setPhone(memberDto.getPhone());
            member.setDOB(memberDto.getDOB());
            member.setAddress(memberDto.getAddress());
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
}