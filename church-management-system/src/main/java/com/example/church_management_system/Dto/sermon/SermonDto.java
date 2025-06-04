package com.example.church_management_system.Dto.sermon;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
public class SermonDto {
    private Long id;

    private String title;
    private String speaker;
    @CreationTimestamp
    private LocalDate date;
    private String url;
    private String description;
}
