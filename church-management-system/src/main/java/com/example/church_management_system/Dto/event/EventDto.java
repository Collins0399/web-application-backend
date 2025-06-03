package com.example.church_management_system.Dto.event;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class EventDto {
        private Long id;

        private String title;
        @CreationTimestamp
        private LocalDate date;
        @CreationTimestamp
        private LocalTime time;
        private String location;
        private String description;
        private String responseMessage;
    }
