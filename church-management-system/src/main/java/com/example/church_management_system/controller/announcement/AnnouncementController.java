package com.example.church_management_system.controller.announcement;

import com.example.church_management_system.Dto.announcement.AnnouncementDto;
import com.example.church_management_system.service.announcement.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> createAnnouncement(@RequestBody AnnouncementDto dto) {
        AnnouncementDto created = announcementService.createAnnouncement(dto);
        return ResponseEntity.ok().body("Announcement created successfully: " + created.getTitle());
    }

    // Get an announcement by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getAnnouncementById(@PathVariable Long id) {
        AnnouncementDto dto = announcementService.getAnnouncementById(id);
        return ResponseEntity.ok().body("Announcement fetched successfully:\n" + dto);
    }

    // Get all announcements
    @GetMapping
    public ResponseEntity<?> getAllAnnouncements() {
        List<AnnouncementDto> list = announcementService.getAllAnnouncements();
        if (list.isEmpty()) {
            return ResponseEntity.ok().body("No announcements found.");
        }
        return ResponseEntity.ok().body("Total announcements found: " + list.size() + "\n" + list);
    }

    // Get only active announcements
    @GetMapping("/active")
    public ResponseEntity<?> getActiveAnnouncements() {
        List<AnnouncementDto> activeList = announcementService.getActiveAnnouncements();
        if (activeList.isEmpty()) {
            return ResponseEntity.ok().body("No active announcements at the moment.");
        }
        return ResponseEntity.ok().body("Active announcements:\n" + activeList);
    }

    // Update an announcement
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAnnouncement(@PathVariable Long id, @RequestBody AnnouncementDto dto) {
        AnnouncementDto updated = announcementService.updateAnnouncement(id, dto);
        return ResponseEntity.ok().body("Announcement updated successfully: " + updated.getTitle());
    }

    // Delete an announcement
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnnouncement(@PathVariable Long id) {
        announcementService.deleteAnnouncement(id);
        return ResponseEntity.ok().body("Announcement with ID " + id + " deleted successfully.");
    }
}
