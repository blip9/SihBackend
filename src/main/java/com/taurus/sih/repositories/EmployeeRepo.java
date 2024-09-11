package com.taurus.sih.repositories;

import com.taurus.sih.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    Employee findEmployeeByEmail(String email);
    Employee findEmployeeByEmployeeId(Long id);
}
