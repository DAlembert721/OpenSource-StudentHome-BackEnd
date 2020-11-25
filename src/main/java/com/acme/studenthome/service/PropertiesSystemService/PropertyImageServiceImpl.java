package com.acme.studenthome.service.PropertiesSystemService;

import com.acme.studenthome.domain.model.PropertiesSystem.Property;
import com.acme.studenthome.domain.model.PropertiesSystem.PropertyImage;
import com.acme.studenthome.domain.repository.PropertiesSystemRepository.PropertyImageRepository;
import com.acme.studenthome.domain.repository.PropertiesSystemRepository.PropertyRepository;
import com.acme.studenthome.domain.service.PropertiesSystemService.PropertyImageService;
import com.acme.studenthome.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PropertyImageServiceImpl implements PropertyImageService {

    @Autowired
    private PropertyImageRepository propertyImageRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public PropertyImage createPropertyImage(Long propertyId, PropertyImage propertyImage) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Property", "Id", propertyId));
        propertyImage.setProperty(property);
        return propertyImageRepository.save(propertyImage);
    }

    @Override
    public PropertyImage getPropertyImageById(Long propertyImageId) {
        return propertyImageRepository.findById(propertyImageId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("PropertyImage", "Id", propertyImageId));
    }

    @Override
    public PropertyImage updatePropertyImage(Long propertyImageId, PropertyImage resource) {
        PropertyImage propertyImage = propertyImageRepository.findById(propertyImageId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("PropertyImage", "Id", propertyImageId));
        propertyImage.setUrl(resource.getUrl());
        return propertyImageRepository.save(propertyImage);
    }

    @Override
    public ResponseEntity<?> deletePropertyImage(Long propertyImageId) {
        PropertyImage propertyImage = propertyImageRepository.findById(propertyImageId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("PropertyImage", "Id", propertyImageId));
        propertyImageRepository.delete(propertyImage);
        return ResponseEntity.ok().build();
    }

    @Override
    public PropertyImage getPropertyImageByPropertyIdAndId(Long propertyId, Long propertyImageId) {
        if (!propertyRepository.existsById(propertyId)) {
            throw  new ResourceNotFoundException("Property", "Id", propertyId);
        }
        return propertyImageRepository.findById(propertyImageId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("PropertyImage", "Id", propertyImageId));

    }


    @Override
    public Page<PropertyImage> getAllPropertiesImagesByPropertyId(Long propertyId, Pageable pageable) {
        if (!propertyRepository.existsById(propertyId)) {
            throw  new ResourceNotFoundException("Property", "Id", propertyId);
        }
        return propertyImageRepository.findByPropertyId(propertyId, pageable);
    }
}
