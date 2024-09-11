package com.taurus.sih.controllers;

import com.taurus.sih.model.CheckInOut;
import com.taurus.sih.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/check")
public class CheckingController {
    @Autowired
    CheckService checkService ;

    @Autowired
    private CheckService checkInOutService;

    // Get all check-in/check-out records
    // Get a single check-in/check-out record by id
    @GetMapping("/{id}")
    public ResponseEntity<CheckInOut> getCheckInOutById(@PathVariable Long id) {
        Optional<CheckInOut> checkInOut = checkInOutService.getCheckInOutById(id);
        return checkInOut.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/employee/{id}")
    public List<CheckInOut> getCheckInOutByEmployeeId(@PathVariable Long id){
        List<CheckInOut> checkInOut= checkInOutService.getCheckInOutByEmployeeId(id);
        return checkInOut;
    }

    // Create a new check-in/check-out record
    @PostMapping("checkin")
    public CheckInOut createCheckInOut(@RequestBody CheckInOut checkInOut) {
        return checkInOutService.createCheckInOut(checkInOut);
    }

    @PutMapping("checkout")
    public ResponseEntity<CheckInOut> updateCheckInOut(@RequestBody CheckInOut checkInOutDetails) {

        Optional<CheckInOut> updatedCheckInOut = checkInOutService.updateCheckInOut(checkInOutDetails);
        return updatedCheckInOut.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCheckInOut(@PathVariable Long id) {
        boolean isDeleted = checkInOutService.deleteCheckInOut(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

