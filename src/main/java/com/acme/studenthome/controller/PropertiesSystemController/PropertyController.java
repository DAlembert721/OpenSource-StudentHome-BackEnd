package com.acme.studenthome.controller.PropertiesSystemController;

import com.acme.studenthome.domain.model.PropertiesSystem.Property;
import com.acme.studenthome.domain.model.UserAccountSystem.LandLord;
import com.acme.studenthome.domain.service.PropertiesSystemService.PropertyService;
import com.acme.studenthome.resource.PropertiesSystemResource.PropertyResource;
import com.acme.studenthome.resource.PropertiesSystemResource.SavePropertyResource;
import com.acme.studenthome.resource.UserAccountSystemResource.LandLordResource;
import com.acme.studenthome.resource.UserAccountSystemResource.SaveLandLordResource;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PropertyController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/properties")
    public Page<PropertyResource> getAllProperties(Pageable pageable) {
        Page<Property> propertyPage = propertyService.getAllProperties(pageable);
        List<PropertyResource> resources = propertyPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    private Property convertToEntity(SavePropertyResource resource) {
        return mapper.map(resource, Property.class);
    }

    private PropertyResource convertToResource(Property entity) {
        return mapper.map(entity, PropertyResource.class);
    }


    @PostConstruct
    public void init() {
        mapper.addMappings(new PropertyMap<Property, PropertyResource>() {
            @Override
            protected void configure() {
                map().setLandLordId(source.getLandLord().getId());
                map().setLandLordFirstName(source.getLandLord().getFirstName());
                map().setLandLordLastName(source.getLandLord().getLastName());

            }
        });
    }

}
