package com.example.church_management_system.Dto.announcement;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AnnouncementDto {
    private Long id;

    private String announcementType;
    private String title;
    private  String message;
    private LocalDateTime dateCreated;
    private LocalDate effectiveDate;
    private LocalDate effectiveUntil;
    private String targetAudience;
}
