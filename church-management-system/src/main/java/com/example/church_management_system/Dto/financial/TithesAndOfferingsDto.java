package com.example.church_management_system.Dto.financial;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TithesAndOfferingsDto {
    private Long id;

    private BigDecimal amount;
    private Date dateOfContribution;

}
