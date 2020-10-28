package com.acme.studenthome.service.LocationsSystem;

import com.acme.studenthome.domain.model.LocationsSystem.District;
import com.acme.studenthome.domain.model.LocationsSystem.Province;
import com.acme.studenthome.domain.model.LocationsSystem.Region;
import com.acme.studenthome.domain.repository.LocationsSystemRepository.DistrictRepository;
import com.acme.studenthome.domain.repository.LocationsSystemRepository.ProvinceRepository;
import com.acme.studenthome.domain.repository.LocationsSystemRepository.RegionRepository;
import com.acme.studenthome.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements com.acme.studenthome.domain.service.LocationsSystemService.LocationService {

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private DistrictRepository districtRepository;


    @Override
    public Page<Region> getAllRegions(Pageable pageable) {
        return regionRepository.findAll(pageable);
    }

    @Override
    public Page<Province> getAllProvincesByRegionId(Long regionId, Pageable pageable) {
        if(!regionRepository.existsById(regionId))
            throw new ResourceNotFoundException("Region", "Id", regionId);
        return provinceRepository.findAllProvincesByRegionId(regionId, pageable);
    }

    @Override
    public Page<District> getAllDistrictsByProvinceId(Long provinceId, Pageable pageable) {
        if(!provinceRepository.existsById(provinceId))
            throw new ResourceNotFoundException("Province", "Id", provinceId);
        return districtRepository.findAllDistrictsByProvinceId(provinceId, pageable);
    }
}