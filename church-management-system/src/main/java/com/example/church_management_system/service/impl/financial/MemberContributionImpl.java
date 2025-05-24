package com.example.church_management_system.service.impl.financial;

import com.example.church_management_system.Dto.financial.MemberContributionDto;
import com.example.church_management_system.Models.MemberRegistration;
import com.example.church_management_system.Models.financial.MemberContribution;
import com.example.church_management_system.repository.MemberRegistrationRepository;
import com.example.church_management_system.repository.financial.MemberContributionRepository;
import com.example.church_management_system.service.financial.MemberContributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberContributionImpl implements MemberContributionService {

    @Autowired
    private MemberContributionRepository memberContributionRepository;

    @Autowired
    private MemberRegistrationRepository memberRegistrationRepository;


    @Override
    public MemberContribution saveContribution(MemberContributionDto contributionDto) {
        // Fetch the member entity using the member_id from the DTO
        MemberRegistration member = memberRegistrationRepository.findById((long) contributionDto.getMember_id())
                .orElseThrow(() -> new RuntimeException("Member not found with ID: " + contributionDto.getMember_id()));

        MemberContribution contribution = new MemberContribution();
        contribution.setMember(member);
        contribution.setMemberName(contributionDto.getMemberName());
        contribution.setContributionType(contributionDto.getContributionType());
        contribution.setAmount(contributionDto.getAmount());
        contribution.setModeOfPayment(contributionDto.getModeOfPayment());
        contribution.setDateOfContribution(contributionDto.getDateOfContribution());

        return memberContributionRepository.save(contribution);
    }


    @Override
    public List<MemberContribution> getContributionsByMemberId(Long member_id) {
        return memberContributionRepository.findByMember_Id(member_id);
    }

    @Override
    public MemberContribution updateContribution(Long id, MemberContribution updatedContribution) {
        Optional<MemberContribution> existingContribution = memberContributionRepository.findById(id);
        if (existingContribution.isPresent()) {
            MemberContribution contribution = existingContribution.get();
            contribution.setMemberName(updatedContribution.getMemberName());
            contribution.setContributionType(updatedContribution.getContributionType());
            contribution.setAmount(updatedContribution.getAmount());
            contribution.setDateOfContribution(updatedContribution.getDateOfContribution());
            contribution.setModeOfPayment(updatedContribution.getModeOfPayment());
            return memberContributionRepository.save(contribution);
        } else {
            throw new RuntimeException("Contribution not found with ID: " + id);
        }
    }

    @Override
    public void deleteContribution(Long id) {
        memberContributionRepository.deleteById(id);
    }

    @Override
    public MemberContribution getContributionById(Long id) {
        return memberContributionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contribution not found with ID: " + id));
    }
}