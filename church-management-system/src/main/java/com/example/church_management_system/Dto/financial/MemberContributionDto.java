package com.example.church_management_system.Dto.financial;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class MemberContributionDto {
    private Long id;
    private int member_id;
    private String memberName;
    private String contributionType;
    private BigDecimal amount;
    private String modeOfPayment;
    private Date dateOfContribution;
}
