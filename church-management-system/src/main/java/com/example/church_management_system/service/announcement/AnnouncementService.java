package com.example.church_management_system.service.announcement;

import com.example.church_management_system.Dto.announcement.AnnouncementDto;

import java.util.List;

public interface AnnouncementService  {
    AnnouncementDto createAnnouncement(AnnouncementDto announcementDto);
    AnnouncementDto getAnnouncementById(Long id);
    List<AnnouncementDto> getAllAnnouncements();
    List<AnnouncementDto> getActiveAnnouncements();
    AnnouncementDto updateAnnouncement(Long id, AnnouncementDto announcementDto);
    void deleteAnnouncement(Long id);
}
