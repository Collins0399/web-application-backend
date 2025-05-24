package com.example.church_management_system.controller.financial;

import com.example.church_management_system.Dto.financial.MemberContributionDto;
import com.example.church_management_system.Models.financial.MemberContribution;
import com.example.church_management_system.service.financial.MemberContributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/member-contributions")
public class MemberContributionController {

    @Autowired
    private MemberContributionService memberContributionService;

    // Create a new contribution using DTO
    @PostMapping
    public ResponseEntity<String> saveContribution(@RequestBody MemberContributionDto contributionDto) {
        try {
            memberContributionService.saveContribution(contributionDto);
            return ResponseEntity.ok("Contribution saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error saving contribution: " + e.getMessage());
        }
    }

    // Get all contributions for a specific member
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<MemberContribution>> getContributionsByMemberId(@PathVariable Long memberId) {
        List<MemberContribution> contributions = memberContributionService.getContributionsByMemberId(memberId);
        return contributions.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(contributions);
    }

    // Get a specific contribution by ID
    @GetMapping("/{id}")
    public ResponseEntity<MemberContribution> getContributionById(@PathVariable Long id) {
        MemberContribution contribution = memberContributionService.getContributionById(id);
        return ResponseEntity.ok(contribution);
    }

    // Update an existing contribution (optional: could be converted to DTO too)
    @PutMapping("/{id}")
    public ResponseEntity<String> updateContribution(
            @PathVariable Long id,
            @RequestBody MemberContribution updatedContribution
    ) {
        try {
            memberContributionService.updateContribution(id, updatedContribution);
            return ResponseEntity.ok("Contribution updated successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Delete a contribution
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContribution(@PathVariable Long id) {
        try {
            memberContributionService.deleteContribution(id);
            return ResponseEntity.ok("Contribution deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
