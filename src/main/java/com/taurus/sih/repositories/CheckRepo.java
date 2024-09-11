package com.taurus.sih.repositories;

import com.taurus.sih.model.CheckInOut;
import com.taurus.sih.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CheckRepo extends JpaRepository<CheckInOut, Long> {
    List<CheckInOut> findCheckInOutByEmployee(Employee employee);

    @Query(value = "SELECT * FROM check_in_out_events WHERE employee_id = :employeeId AND DATE(check_in_time) = :date", nativeQuery = true)
    List<CheckInOut> findByEmployeeIdAndDate(@Param("employeeId") Long employeeId, @Param("date") LocalDate date);
}
