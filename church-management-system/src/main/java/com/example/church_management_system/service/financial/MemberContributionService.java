package com.example.church_management_system.service.financial;

import com.example.church_management_system.Dto.financial.MemberContributionDto;
import com.example.church_management_system.Models.financial.MemberContribution;

import java.util.List;

public interface MemberContributionService {
    MemberContribution saveContribution(MemberContributionDto contributionDto);
    List<MemberContribution> getContributionsByMemberId(Long memberId);
    MemberContribution updateContribution(Long id, MemberContribution updatedContribution);
    void deleteContribution(Long id);
    MemberContribution getContributionById(Long id);
}
