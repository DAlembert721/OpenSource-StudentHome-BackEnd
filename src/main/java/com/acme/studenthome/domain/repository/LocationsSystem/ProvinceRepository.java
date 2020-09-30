package com.acme.studenthome.domain.repository.LocationsSystem;

import com.acme.studenthome.domain.model.LocationsSystem.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepository extends JpaRepository<Province, Long> {
     Page<Province> findAllProvincesByRegionId(Long regionId, Pageable pageable);

}