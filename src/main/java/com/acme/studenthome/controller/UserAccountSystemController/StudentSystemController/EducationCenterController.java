package com.acme.studenthome.controller.UserAccountSystemController.StudentSystemController;

import com.acme.studenthome.domain.model.UserAccountSystem.StudentSystem.EducationCenter;
import com.acme.studenthome.domain.service.UserAccountSystemService.StudentSystemService.EducationCenterService;
import com.acme.studenthome.resource.UserAccountSystemResource.StudentSystemResource.SaveEducationCenterResource;
import com.acme.studenthome.resource.UserAccountSystemResource.StudentSystemResource.EducationCenterResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/education")
public class EducationCenterController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private EducationCenterService educationCenterService;

    @Operation(summary = "Get Education Center",
            description = "Get a Education Center",
            tags = "educationCenters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Education Center found",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/education-centers/{educationCenterId}")
    public EducationCenterResource getEducationCenterById(@PathVariable(name = "educationCenterId") Long educationCenterId) {
        return convertToResource(educationCenterService.getEducationCenterById(educationCenterId));
    }

    @Operation(summary = "Get All Education Center",
            description = "Get all Education Center",
            tags = "educationCenters")
    @GetMapping("/education-centers")
    public Page<EducationCenterResource> getAllEducationCenters(Pageable pageable) {
        Page<EducationCenter> page = educationCenterService.getAllEducationCenters(pageable);
        List<EducationCenterResource> list = page.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(list, pageable, list.size());
    }

    @Operation(summary = "Post Education Center",
            description = "Create a new Education Center",
            tags = "educationCenters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Education Center successfully created",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/districts/{districtId}/education-centers")
    public EducationCenterResource createEducationCenter(@PathVariable(name = "districtId") Long districtId,
            @Valid @RequestBody SaveEducationCenterResource educationCenterRequest) {
      EducationCenter educationCenter = convertToEntity(educationCenterRequest);
        return convertToResource(educationCenterService.createEducationCenter(districtId, educationCenter));
    }

    @Operation(summary = "Put Education Center",
            description = "Update a Education Center",
            tags = "educationCenters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Education Center info successfully updated",
                    content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/districts/{districtId}/education-centers/{educationCenterId}")
    public EducationCenterResource updateEducationCenter(@PathVariable(name = "educationCenterId") Long educationCenterId,
                                                         @PathVariable(name = "districtId") Long districtId,
                                                         @Valid @RequestBody SaveEducationCenterResource resource){
        EducationCenter educationCenter = convertToEntity(resource);
        return convertToResource(educationCenterService.updateEducationCenter(educationCenterId, districtId, educationCenter));
    }

    @Operation(summary = "Delete Education Center",
            description = "Delete a Education Center",
            tags = "educationCenters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Education Center successfully deleted",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/education-centers/{educationCenterId}")
    public ResponseEntity<?> deleteResponse(@PathVariable(name = "educationCenterId") Long educationCenterId) {
        return educationCenterService.deleteEducationCenter(educationCenterId);
    }

    private EducationCenter convertToEntity(SaveEducationCenterResource resource) {
        return mapper.map(resource, EducationCenter.class);
    }

    private EducationCenterResource convertToResource(EducationCenter entity) {

        return mapper.map(entity, EducationCenterResource.class);
    }

    @PostConstruct
    public void init(){
        mapper.addMappings(new PropertyMap<EducationCenter, EducationCenterResource>() {
            @Override
            protected void configure() {
                map().setDistrictName(source.getDistrict().getName());
            }
        });
    }
}
