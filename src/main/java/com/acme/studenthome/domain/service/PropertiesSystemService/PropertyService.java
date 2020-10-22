package com.acme.studenthome.domain.service.PropertiesSystemService;

import com.acme.studenthome.domain.model.PropertiesSystem.Property;
import com.acme.studenthome.domain.model.UserAccountSystem.LandLord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PropertyService {
    Page<Property> getAllProperties(Pageable pageable);
    Property createProperty(Long landLordId, Long districtId, Property property);
    Property getPropertyById(Long propertyId);
    Property updateProperty(Long landLordId, Long propertyId, Property propertyRequest);
    ResponseEntity<?> deleteProperty(Long landLordId, Long propertyId);
}
