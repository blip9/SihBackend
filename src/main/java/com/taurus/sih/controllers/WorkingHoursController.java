package com.taurus.sih.controllers;

import com.taurus.sih.model.Employee;
import com.taurus.sih.repositories.EmployeeRepo;
import com.taurus.sih.service.WorkingHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/working-hours")
public class WorkingHoursController {

    @Autowired
    private WorkingHoursService workingHoursService;

    @Autowired
    EmployeeRepo empRepo;

    @GetMapping("/calculate/{employeeId}/{date}")
    public ResponseEntity<Double> calculateWorkingHours(@PathVariable Long employeeId, @PathVariable String date) {
        Employee employee = empRepo.findById(employeeId).orElseThrow(() -> new UsernameNotFoundException("Employee not found"));

        LocalDate localDate = LocalDate.parse(date);
        double totalWorkingHours = workingHoursService.calculateTotalWorkingHours(employee.getEmployeeId(), localDate);

        return ResponseEntity.ok(totalWorkingHours);
    }
}

