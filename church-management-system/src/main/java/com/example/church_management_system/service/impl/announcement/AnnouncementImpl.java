package com.example.church_management_system.service.impl.announcement;

import com.example.church_management_system.Dto.announcement.AnnouncementDto;
import com.example.church_management_system.Models.announcement.Announcement;
import com.example.church_management_system.repository.announcement.AnnouncementRepository;
import com.example.church_management_system.service.announcement.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnnouncementImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    @Autowired
    public AnnouncementImpl(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    @Override
    public AnnouncementDto createAnnouncement(AnnouncementDto dto) {
        Announcement announcement = new Announcement();
        announcement.setTitle(dto.getTitle());
        announcement.setMessage(dto.getMessage());
        announcement.setDateCreated(LocalDateTime.now());
        announcement.setEffectiveDate(dto.getEffectiveDate());
        announcement.setEffectiveUntil(dto.getEffectiveUntil());
        announcement.setTargetAudience(dto.getTargetAudience());
        announcement.setAnnouncementType(dto.getAnnouncementType());

        Announcement savedAnnouncement = announcementRepository.save(announcement);
        return toDto(savedAnnouncement);
    }

    @Override
    public AnnouncementDto getAnnouncementById(Long id) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Announcement not found with ID: " + id));
        return toDto(announcement);
    }

    @Override
    public List<AnnouncementDto> getAllAnnouncements() {
        return announcementRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AnnouncementDto> getActiveAnnouncements() {
        LocalDate now = LocalDate.now();
        List<Announcement> activeAnnouncements = announcementRepository.findAll().stream()
                .filter(a -> (a.getEffectiveDate() != null && !a.getEffectiveDate().isAfter(now)) &&
                        (a.getEffectiveUntil() == null || !a.getEffectiveUntil().isBefore(now)))
                .collect(Collectors.toList());

        return activeAnnouncements.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AnnouncementDto updateAnnouncement(Long id, AnnouncementDto dto) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Announcement not found with ID: " + id));

        announcement.setTitle(dto.getTitle());
        announcement.setMessage(dto.getMessage());
        announcement.setEffectiveDate(dto.getEffectiveDate());
        announcement.setEffectiveUntil(dto.getEffectiveUntil());
        announcement.setTargetAudience(dto.getTargetAudience());
        announcement.setAnnouncementType(dto.getAnnouncementType());

        Announcement updatedAnnouncement = announcementRepository.save(announcement);
        return toDto(updatedAnnouncement);
    }

    @Override
    public void deleteAnnouncement(Long id) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Announcement not found with ID: " + id));
        announcementRepository.delete(announcement);
    }

    // Helper method using default constructor + setters
    private AnnouncementDto toDto(Announcement announcement) {
        AnnouncementDto dto = new AnnouncementDto();
        dto.setId(announcement.getId());
        dto.setTitle(announcement.getTitle());
        dto.setMessage(announcement.getMessage());
        dto.setDateCreated(announcement.getDateCreated());
        dto.setEffectiveDate(announcement.getEffectiveDate());
        dto.setEffectiveUntil(announcement.getEffectiveUntil());
        dto.setTargetAudience(announcement.getTargetAudience());
        dto.setAnnouncementType(announcement.getAnnouncementType());
        return dto;
    }
}
