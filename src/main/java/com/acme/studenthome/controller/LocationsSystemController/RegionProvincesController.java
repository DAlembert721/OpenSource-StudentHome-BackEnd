package com.acme.studenthome.controller.LocationsSystemController;

import com.acme.studenthome.domain.model.LocationsSystem.Province;
import com.acme.studenthome.domain.service.LocationsSystemService.LocationService;
import com.acme.studenthome.resource.LocationsSystem.ProvinceResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
public class RegionProvincesController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private LocationService locationService;

    @Operation(summary = "Get Provinces of a Region",
            description = "Get All Provinces of a Region by Page",
            tags = "regions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All provinces of a region returned",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/regions/{regionId}/provinces")
    public Page<ProvinceResource> getAllProvincesByRegionId(@PathVariable Long regionId, Pageable pageable) {
        Page<Province> provincePage = locationService.getAllProvincesByRegionId(regionId, pageable);
        List<ProvinceResource> resources = provincePage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    private ProvinceResource convertToResource(Province entity) {
        return mapper.map(entity, ProvinceResource.class);
    }

}