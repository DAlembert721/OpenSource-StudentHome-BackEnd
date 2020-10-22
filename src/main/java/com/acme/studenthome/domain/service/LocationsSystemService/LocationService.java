package com.acme.studenthome.domain.service.LocationsSystemService;

import com.acme.studenthome.domain.model.LocationsSystem.District;
import com.acme.studenthome.domain.model.LocationsSystem.Province;
import com.acme.studenthome.domain.model.LocationsSystem.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LocationService {
     Page<Region> getAllRegions(Pageable pageable);
     Page<Province> getAllProvincesByRegionId(Long regionId, Pageable pageable);
     Page<District> getAllDistrictsByProvinceId(Long provinceId, Pageable pageable);
}