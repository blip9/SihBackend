package com.taurus.sih.service;

import com.taurus.sih.SihApplication;
import com.taurus.sih.model.CheckInOut;
import com.taurus.sih.model.Employee;
import com.taurus.sih.repositories.CheckRepo;
import com.taurus.sih.repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CheckService {
    @Autowired
    CheckRepo repo ;
    public void saveCheckPoint(CheckInOut checkInOut) {

        repo.save(checkInOut);
    }

    public Optional<CheckInOut> getCheckInOutById(Long id) {
        return repo.findById(id);
    }

    public CheckInOut createCheckInOut(CheckInOut checkInOut) {
        repo.save(checkInOut);
        return checkInOut;
    }

    public Optional<CheckInOut> updateCheckInOut(CheckInOut checkInOutDetails) {
        Long id = checkInOutDetails.getEventId();
        return repo.findById(id).map(checkInOut -> {
            // Updating fields that are allowed to be updated
            checkInOut.setCheckOutTime(checkInOutDetails.getCheckOutTime());
            checkInOut.setCheckOutLatitude(checkInOutDetails.getCheckOutLatitude());
            checkInOut.setCheckOutLongitude(checkInOutDetails.getCheckOutLongitude());
            checkInOut.setManual(checkInOutDetails.isManual());
            return repo.save(checkInOut);  // Saving the updated entity
        });
    }

    public boolean deleteCheckInOut(Long id) {
        if(repo.findById(id).isPresent()){
            repo.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
    @Autowired
    EmployeeRepo empRepo;
    public List<CheckInOut> getCheckInOutByEmployeeId(Long id) {

        Employee employee = empRepo.findEmployeeByEmployeeId(id);
        List<CheckInOut> checkInOutList = repo.findCheckInOutByEmployee(employee);
        return checkInOutList;
    }
}
