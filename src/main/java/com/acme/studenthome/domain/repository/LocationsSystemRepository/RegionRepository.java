package com.acme.studenthome.domain.repository.LocationsSystemRepository;

import com.acme.studenthome.domain.model.LocationsSystem.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region,Long> {

}
