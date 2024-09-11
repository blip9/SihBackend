package com.taurus.sih.service;

import com.taurus.sih.model.OfficeLocations;
import com.taurus.sih.repositories.OfficeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfficeService {
    @Autowired
    private OfficeRepo officeLocationsRepository;

    // Get all office locations
    public List<OfficeLocations> getAllOfficeLocations() {
        return officeLocationsRepository.findAll();
    }

    // Get office location by ID
    public Optional<OfficeLocations> getOfficeLocationById(Long id) {
        return officeLocationsRepository.findById(id);
    }

    // Create a new office location
    public OfficeLocations createOfficeLocation(OfficeLocations officeLocation) {
        return officeLocationsRepository.save(officeLocation);
    }

    // Update an existing office location
    public Optional<OfficeLocations> updateOfficeLocation(Long id, OfficeLocations officeLocationDetails) {
        Optional<OfficeLocations> existingOfficeLocation = officeLocationsRepository.findById(id);

        if (existingOfficeLocation.isPresent()) {
            OfficeLocations officeLocation = existingOfficeLocation.get();
            officeLocation.setOfficeName(officeLocationDetails.getOfficeName());
            officeLocation.setLatitude(officeLocationDetails.getLatitude());
            officeLocation.setLongitude(officeLocationDetails.getLongitude());
            officeLocation.setRadius(officeLocationDetails.getRadius());
            officeLocation.setAddress(officeLocationDetails.getAddress());

            return Optional.of(officeLocationsRepository.save(officeLocation));
        }

        return Optional.empty();
    }

    // Delete office location by ID
    public boolean deleteOfficeLocation(Long id) {
        if (officeLocationsRepository.existsById(id)) {
            officeLocationsRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
