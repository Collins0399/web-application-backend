package com.example.church_management_system.controller.sermon;

import com.example.church_management_system.Dto.sermon.SermonDto;
import com.example.church_management_system.service.sermon.SermonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")  // Enable CORS for frontend
@RequestMapping("/api/sermons")
public class SermonController {

    @Autowired
    private SermonService sermonService;

    // Create Sermon
    @PostMapping
    public ResponseEntity<SermonDto> createSermon(@RequestBody SermonDto sermonDto) {
        SermonDto created = sermonService.createSermon(sermonDto);
        return ResponseEntity.ok(created);
    }

    // Update Sermon
    @PutMapping("/{id}")
    public ResponseEntity<SermonDto> updateSermon(@PathVariable Long id, @RequestBody SermonDto sermonDto) {
        SermonDto updated = sermonService.updateSermon(id, sermonDto);
        return ResponseEntity.ok(updated);
    }

    // Delete Sermon
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSermon(@PathVariable Long id) {
        sermonService.deleteSermon(id);
        return ResponseEntity.ok("Sermon deleted successfully");
    }

    // Get All Sermons
    @GetMapping
    public ResponseEntity<List<SermonDto>> getAllSermons() {
        List<SermonDto> sermons = sermonService.findAllSermons();
        return ResponseEntity.ok(sermons);
    }

    // Get Sermon by ID
    @GetMapping("/{id}")
    public ResponseEntity<SermonDto> getSermonById(@PathVariable Long id) {
        SermonDto sermon = sermonService.getSermonById(id);
        return ResponseEntity.ok(sermon);
    }
}
