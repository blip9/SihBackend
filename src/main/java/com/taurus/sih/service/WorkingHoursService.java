package com.taurus.sih.service;

import com.taurus.sih.model.CheckInOut;
import com.taurus.sih.model.Employee;
import com.taurus.sih.repositories.CheckRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Service
public class WorkingHoursService {
    @Autowired
    private CheckRepo checkInOutRepo;

    public double calculateTotalWorkingHours(Long employeeId, LocalDate date) {
        // Get all check-in/out records for the employee on the specified date
        List<CheckInOut> checkInOutRecords = checkInOutRepo.findByEmployeeIdAndDate(employeeId, date);
        System.out.println(checkInOutRecords);
        double totalHours = 0;

        for (CheckInOut record : checkInOutRecords) {
            Timestamp checkInTime = record.getCheckInTime();
            Timestamp checkOutTime = record.getCheckOutTime();

            // If the employee hasn't checked out, consider the current time as check-out
            if (checkOutTime == null) {
                checkOutTime = new Timestamp(System.currentTimeMillis());
            }

            // Calculate the duration between check-in and check-out
            Duration duration = Duration.between(checkInTime.toLocalDateTime(), checkOutTime.toLocalDateTime());
            System.out.println(duration);
            // Convert the duration to hours and add to total
            totalHours += duration.toMinutes() / 60.0;
        }

        return totalHours;
    }
}
