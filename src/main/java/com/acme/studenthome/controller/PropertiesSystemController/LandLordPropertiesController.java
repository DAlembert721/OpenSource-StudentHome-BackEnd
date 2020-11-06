package com.acme.studenthome.controller.PropertiesSystemController;

import com.acme.studenthome.domain.model.PropertiesSystem.Property;
import com.acme.studenthome.domain.model.UserAccountSystem.LandLord;
import com.acme.studenthome.domain.service.PropertiesSystemService.PropertyService;
import com.acme.studenthome.resource.PropertiesSystemResource.PropertyResource;
import com.acme.studenthome.resource.PropertiesSystemResource.SavePropertyResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class LandLordPropertiesController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PropertyService propertyService;

    @Operation(summary = "Post Property of a Land Lord",
            description = "Post AProperty of a Land Lord",
            tags = "landlords")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Property created",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("landlords/{landLordId}/properties")
    public PropertyResource createProperty(
            @PathVariable(name = "landLordId") Long landLordId,
            @Valid @RequestBody SavePropertyResource resource) {
        Property property = convertToEntity(resource);
        return convertToResource(propertyService.createProperty(landLordId,resource.getPlace(), property));

    }

    private Property convertToEntity(SavePropertyResource resource) {
        return mapper.map(resource, Property.class);
    }

    private PropertyResource convertToResource(Property entity) {
        return mapper.map(entity, PropertyResource.class);
    }


}
