package com.acme.studenthome.controller.PropertiesSystemController;

import com.acme.studenthome.domain.model.PropertiesSystem.Contract;
import com.acme.studenthome.domain.model.PropertiesSystem.PropertyImage;
import com.acme.studenthome.domain.service.PropertiesSystemService.PropertyImageService;
import com.acme.studenthome.resource.PropertiesSystemResource.ContractResource;
import com.acme.studenthome.resource.PropertiesSystemResource.PropertyImageResource;
import com.acme.studenthome.resource.PropertiesSystemResource.SavePropertyImageResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PropertyPropertyImagesController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PropertyImageService propertyImageService;


    @Operation(summary = "Get All PropertyImages By PropertyId",
            description = "Get all PropertyImages for a property given a PropertyId",
            tags = {"properties"})
    @GetMapping("/properties/{propertyId}/property-images")
    public Page<PropertyImageResource> getAllPropertyImageByPropertyId(
            @PathVariable(name = "propertyId") Long propertyId,
            Pageable pageable){
        Page<PropertyImage> propertyImagePage = propertyImageService.getAllPropertiesImagesByPropertyId(propertyId, pageable);
        List<PropertyImageResource> propertyImageResources = propertyImagePage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(propertyImageResources, pageable, propertyImageResources.size());
    }

    private PropertyImage convertToEntity(SavePropertyImageResource resource) {
        return mapper.map(resource, PropertyImage.class);
    }

    private PropertyImageResource convertToResource(PropertyImage entity) {
        return mapper.map(entity, PropertyImageResource.class);
    }

}
