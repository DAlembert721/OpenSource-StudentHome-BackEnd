package com.acme.studenthome.controller.PropertiesSystemController;

import com.acme.studenthome.domain.model.PropertiesSystem.Property;
import com.acme.studenthome.domain.service.PropertiesSystemService.PropertyService;
import com.acme.studenthome.resource.PropertiesSystemResource.PropertyResource;
import com.acme.studenthome.resource.PropertiesSystemResource.SavePropertyResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api")
public class PropertyServicesController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PropertyService propertyService;

    @Operation(summary = "Assign Service To Property", description = "Add a new service to an existing property", tags = "properties")
    @PostMapping("/properties/{propertyId}/services/{serviceId}")
    public PropertyResource assignPropertyService(
            @PathVariable(name = "propertyId") Long propertyId,
            @PathVariable(name = "serviceId") Long serviceId) {
        return convertToResource(propertyService.assignPropertyService(propertyId, serviceId));
    }

    @Operation(summary = "UnAssign Service To Property", description = "Remove an existing service to an existing property", tags = "properties")
    @DeleteMapping("/properties/{propertyId}/services/{serviceId}")
    public PropertyResource unAssignPropertyService(
            @PathVariable(name = "propertyId") Long propertyId,
            @PathVariable(name = "serviceId") Long serviceId) {
        return convertToResource(propertyService.unAssignPropertyService(propertyId, serviceId));
    }

    private Property convertToEntity(SavePropertyResource resource) {
        return mapper.map(resource, Property.class);
    }

    private PropertyResource convertToResource(Property entity) {
        return mapper.map(entity, PropertyResource.class);
    }


}
