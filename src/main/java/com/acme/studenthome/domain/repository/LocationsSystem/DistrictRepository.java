package com.acme.studenthome.domain.repository.LocationsSystem;

import com.acme.studenthome.domain.model.LocationsSystem.District;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<District, Long> {
}
