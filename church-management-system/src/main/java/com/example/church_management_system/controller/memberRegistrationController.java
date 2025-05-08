package com.example.church_management_system.controller;

import com.example.church_management_system.Dto.memberRegistrationDto;
import com.example.church_management_system.Models.memberRegistration;
import com.example.church_management_system.repository.memberRegistrationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/members")
public class memberRegistrationController {

    private final memberRegistrationRepository memberRegistrationRepository;

    public memberRegistrationController(memberRegistrationRepository memberRegistrationRepository) {
        this.memberRegistrationRepository = memberRegistrationRepository;
    }

    // Get all members
    @GetMapping
    public ResponseEntity<?> getAllMembers() {
        return ResponseEntity.ok(memberRegistrationRepository.findAll());
    }

    // Create a new member
    @PostMapping
    public ResponseEntity<String> createMember(@RequestBody memberRegistrationDto memberDto) {
        memberRegistration member = new memberRegistration();
        member.setFullName(memberDto.getFullName());
        member.setEmail(memberDto.getEmail());
        member.setPhone(memberDto.getPhone());
        member.setDob(memberDto.getDOB());
        member.setAddress(memberDto.getAddress());
        member.setDateJoined(memberDto.getDateJoined());
        memberRegistrationRepository.save(member);
        return ResponseEntity.ok("Member created successfully");
    }

    // Update an existing member
    @PutMapping("/{id}")
    public ResponseEntity<String> updateMember(@PathVariable Long id, @RequestBody memberRegistrationDto memberDto) {
        return memberRegistrationRepository.findById(id).map(existingMember -> {
            existingMember.setFullName(memberDto.getFullName());
            existingMember.setEmail(memberDto.getEmail());
            existingMember.setPhone(memberDto.getPhone());
            existingMember.setDob(memberDto.getDOB());
            existingMember.setAddress(memberDto.getAddress());
            existingMember.setDateJoined(memberDto.getDateJoined());
            memberRegistrationRepository.save(existingMember);
            return ResponseEntity.ok("Member updated successfully");
        }).orElse(ResponseEntity.badRequest().body("Invalid member ID"));
    }

    // Delete a member
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id) {
        if (memberRegistrationRepository.existsById(id)) {
            memberRegistrationRepository.deleteById(id);
            return ResponseEntity.ok("Member deleted successfully");
        }
        return ResponseEntity.badRequest().body("Invalid member ID");
    }
}