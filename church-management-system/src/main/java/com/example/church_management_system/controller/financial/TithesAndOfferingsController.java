package com.example.church_management_system.controller.financial;

import com.example.church_management_system.Models.financial.TithesAndOfferings;
import com.example.church_management_system.service.financial.TithesAndOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class TithesAndOfferingsController {

    @Autowired
    private TithesAndOfferingService tithesAndOfferingService;

    @PostMapping("/tithes-offerings")
    public ResponseEntity<String> saveTithesAndOfferings(@RequestBody TithesAndOfferings tithesAndOfferings) {
        TithesAndOfferings saved = tithesAndOfferingService.saveTithesAndOfferings(tithesAndOfferings);
        return ResponseEntity.ok("Tithes and Offerings saved successfully");
    }

    @GetMapping("/tithes-offerings")
    public ResponseEntity<List<TithesAndOfferings>> getAllTithesAndOfferings() {
        List<TithesAndOfferings> allRecords = tithesAndOfferingService.getAllTithesAndOfferings();
        return ResponseEntity.ok(allRecords);
    }

    @GetMapping("/tithes-offerings/{id}")
    public ResponseEntity<Object> getTithesAndOfferingsById(@PathVariable Long id) {
        Optional<TithesAndOfferings> record = tithesAndOfferingService.getTithesAndOfferingsById(id);
        return record.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body("Tithes and Offerings not found with ID: " + id));
    }

    @PutMapping("/tithes-offerings/{id}")
    public ResponseEntity<String> updateTithesAndOfferings(@PathVariable Long id, @RequestBody TithesAndOfferings tithesAndOfferings) {
        try {
            TithesAndOfferings updated = tithesAndOfferingService.updateTithesAndOfferings(id, tithesAndOfferings);
            return ResponseEntity.ok("Tithes and Offerings updated successfully with ID: " + updated.getId());
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Tithes and Offerings not found with ID: " + id);
        }
    }

    @DeleteMapping("/tithes-offerings/{id}")
    public ResponseEntity<String> deleteTithesAndOfferings(@PathVariable Long id) {
        try {
            tithesAndOfferingService.deleteTithesAndOfferings(id);
            return ResponseEntity.ok("Tithes and Offerings deleted successfully with ID: " + id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Tithes and Offerings not found with ID: " + id);
        }
    }
}