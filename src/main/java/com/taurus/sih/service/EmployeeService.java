package com.taurus.sih.service;

import com.taurus.sih.model.Employee;
import com.taurus.sih.repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo repo;

    public void addEmployee(Employee employee) {
        repo.save(employee);
    }

    public Employee getEmployee(String email) {
        Employee employee = repo.findEmployeeByEmail(email);
        return employee;
    }
}
