package com.example.church_management_system.Dto.Auth;

import lombok.Data;

@Data
public class RegisterDto {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
}
