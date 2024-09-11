package com.taurus.sih.repositories;

import com.taurus.sih.model.OfficeLocations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepo extends JpaRepository<OfficeLocations,Long> {

}
