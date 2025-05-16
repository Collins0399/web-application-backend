package com.example.church_management_system.controller;

import com.example.church_management_system.Models.MemberRegistration;
import com.example.church_management_system.repository.MemberRegistrationRepository;
import com.example.church_management_system.service.MemberRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.church_management_system.Dto.MemberRegistrationDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/members")
public class MemberRegistrationController {

    private final MemberRegistrationRepository memberRegistrationRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberRegistrationService memberRegistrationService;

    public MemberRegistrationController(MemberRegistrationRepository memberRegistrationRepository, PasswordEncoder passwordEncoder, MemberRegistrationService memberRegistrationService) {
        this.memberRegistrationRepository = memberRegistrationRepository;
        this.passwordEncoder = passwordEncoder;
        this.memberRegistrationService = memberRegistrationService;
    }

    // Get all members
    @GetMapping
    public ResponseEntity<?> getAllMembers() {
        return ResponseEntity.ok(memberRegistrationRepository.findAll());
    }

    // Create a new member
    @PostMapping
    public ResponseEntity<Map<String, Object>> createMember(@RequestBody MemberRegistrationDto memberDto) {
        Map<String, Object> response = new HashMap<>();

        // Call service only for validation and preparing encoded password
        MemberRegistrationDto preparedDto = memberRegistrationService.createMember(memberDto);

        // Create entity and save
        MemberRegistration member = new MemberRegistration();
        member.setFullName(preparedDto.getFullName());
        member.setEmail(preparedDto.getEmail());
        member.setPhone(preparedDto.getPhone());
        member.setDob(preparedDto.getDob());
        member.setAddress(preparedDto.getAddress());
        member.setDateJoined(preparedDto.getDateJoined() != null ? preparedDto.getDateJoined() : LocalDate.now());
        member.setPassword(preparedDto.getPassword());

        memberRegistrationRepository.save(member);

        response.put("status", "success");
        response.put("message", "Member created successfully");
        return ResponseEntity.ok(response);
    }


    // Update an existing member
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateMember(@PathVariable Long id, @RequestBody MemberRegistrationDto memberDto) {
        Map<String, Object> response = new HashMap<>();

        if (!memberDto.getPassword().equals(memberDto.getConfirmPassword())) {
            response.put("status", "error");
            response.put("message", "Password and Confirm Password do not match");
            return ResponseEntity.badRequest().body(response);
        }

        return memberRegistrationRepository.findById(id).map(existingMember -> {
            existingMember.setFullName(memberDto.getFullName());
            existingMember.setEmail(memberDto.getEmail());
            existingMember.setPhone(memberDto.getPhone());
            existingMember.setDob(memberDto.getDob());
            existingMember.setAddress(memberDto.getAddress());
            existingMember.setDateJoined(memberDto.getDateJoined());
            existingMember.setPassword(passwordEncoder.encode(memberDto.getPassword()));
            memberRegistrationRepository.save(existingMember);

            response.put("status", "success");
            response.put("message", "Member updated successfully");
            response.put("member", existingMember);
            return ResponseEntity.ok(response);
        }).orElseGet(() -> {
            response.put("status", "error");
            response.put("message", "Member not found with ID: " + id);
            return ResponseEntity.badRequest().body(response);
        });
    }

    // Delete a member
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteMember(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        if (memberRegistrationRepository.existsById(id)) {
            memberRegistrationRepository.deleteById(id);
            response.put("status", "success");
            response.put("message", "Member deleted successfully");
            return ResponseEntity.ok(response);
        }

        response.put("status", "error");
        response.put("message", "Member not found with ID: " + id);
        return ResponseEntity.badRequest().body(response);
    }
}