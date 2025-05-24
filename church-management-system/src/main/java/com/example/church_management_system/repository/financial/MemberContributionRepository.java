package com.example.church_management_system.repository.financial;

import com.example.church_management_system.Models.financial.MemberContribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberContributionRepository extends JpaRepository <MemberContribution, Long>  {
    List<MemberContribution> findByMember_Id(Long memberId);
}
