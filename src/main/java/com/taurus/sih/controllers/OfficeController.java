package com.taurus.sih.controllers;

import com.taurus.sih.model.OfficeLocations;
import com.taurus.sih.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/office-locations")

public class OfficeController {
    @Autowired
    private OfficeService officeLocationsService;

    // Get all office locations
    @GetMapping
    public List<OfficeLocations> getAllOfficeLocations() {
        return officeLocationsService.getAllOfficeLocations();
    }

    // Get a specific office location by ID
    @GetMapping("/{id}")
    public ResponseEntity<OfficeLocations> getOfficeLocationById(@PathVariable Long id) {
        Optional<OfficeLocations> officeLocation = officeLocationsService.getOfficeLocationById(id);
        return officeLocation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new office location
    @PostMapping("/addNewOffice")
    public OfficeLocations createOfficeLocation(@RequestBody OfficeLocations officeLocations) {
        return officeLocationsService.createOfficeLocation(officeLocations);
    }

    // Update an existing office location
    @PutMapping("/{id}")
    public ResponseEntity<OfficeLocations> updateOfficeLocation(@PathVariable Long id, @RequestBody OfficeLocations officeLocationDetails) {
        Optional<OfficeLocations> updatedOfficeLocation = officeLocationsService.updateOfficeLocation(id, officeLocationDetails);
        return updatedOfficeLocation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete an office location by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOfficeLocation(@PathVariable Long id) {
        boolean isDeleted = officeLocationsService.deleteOfficeLocation(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
