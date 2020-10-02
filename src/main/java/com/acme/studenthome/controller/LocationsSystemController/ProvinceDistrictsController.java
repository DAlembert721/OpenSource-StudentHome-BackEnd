package com.acme.studenthome.controller.LocationsSystemController;

import com.acme.studenthome.domain.model.LocationsSystem.District;
import com.acme.studenthome.domain.service.LocationsSystemService.LocationService;
import com.acme.studenthome.resource.LocationsSystem.DistrictResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProvinceDistrictsController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private LocationService locationService;

    @GetMapping("/provinces/{provinceId}/districts")
    public Page<DistrictResource> getAllDistrictsByProvinceId(@PathVariable Long provinceId, Pageable pageable) {
        Page<District> districtPage = locationService.getAllDistrictsByProvinceId(provinceId, pageable);
        List<DistrictResource> resources = districtPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    private DistrictResource convertToResource(District entity) {
        return mapper.map(entity, DistrictResource.class);
    }


}
