package com.acme.studenthome.domain.repository.LocationsSystemRepository;

import com.acme.studenthome.domain.model.LocationsSystem.District;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<District, Long> {
     Page<District> findAllDistrictsByProvinceId(Long provinceId, Pageable pageable);
}