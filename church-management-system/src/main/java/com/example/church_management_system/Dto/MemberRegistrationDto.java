package com.example.church_management_system.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberRegistrationDto {
    private Long id;
    private String memberId;
    private String fullName;
    private String email;
    private String phone;
    private LocalDate dob;
    private String address;
    private LocalDate dateJoined;
    private String password;
    private String confirmPassword;
}