package com.example.church_management_system.Models.announcement;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "announcement")
public class Announcement {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String announcementType;
    private String title;
    private  String message;
    private LocalDateTime dateCreated;
    private LocalDate effectiveDate;
    private LocalDate effectiveUntil;
    private String targetAudience;
}
