package com.example.church_management_system.controller.announcement;

import com.example.church_management_system.Dto.announcement.AnnouncementDto;
import com.example.church_management_system.service.announcement.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/announcements")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @Autowired
    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    // Create a new announcement
    @PostMapping
    public ResponseEntity<AnnouncementDto> createAnnouncement(@RequestBody AnnouncementDto dto) {
        AnnouncementDto created = announcementService.createAnnouncement(dto);
        return ResponseEntity.ok(created);
    }

    // Get an announcement by ID
    @GetMapping("/{id}")
    public ResponseEntity<List<AnnouncementDto>> getAnnouncementById(@PathVariable Long id) {
        AnnouncementDto dto = announcementService.getAnnouncementById(id);
        if (dto == null) {
            return ResponseEntity.ok(Collections.emptyList());
        }
        return ResponseEntity.ok(Collections.singletonList(dto));
    }

    // Get all announcements
    @GetMapping
    public ResponseEntity<List<AnnouncementDto>> getAllAnnouncements() {
        List<AnnouncementDto> list = announcementService.getAllAnnouncements();
        return ResponseEntity.ok(list);
    }

    // Get only active announcements
    @GetMapping("/active")
    public ResponseEntity<List<AnnouncementDto>> getActiveAnnouncements() {
        List<AnnouncementDto> activeList = announcementService.getActiveAnnouncements();
        return ResponseEntity.ok(activeList);
    }

    // Update an announcement
    @PutMapping("/{id}")
    public ResponseEntity<AnnouncementDto> updateAnnouncement(@PathVariable Long id, @RequestBody AnnouncementDto dto) {
        AnnouncementDto updated = announcementService.updateAnnouncement(id, dto);
        return ResponseEntity.ok(updated);
    }

    // Delete an announcement
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnnouncement(@PathVariable Long id) {
        announcementService.deleteAnnouncement(id);
        return ResponseEntity.noContent().build();
    }
}