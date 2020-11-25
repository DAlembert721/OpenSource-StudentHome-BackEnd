package com.acme.studenthome.service.PropertiesSystemService;

import com.acme.studenthome.domain.model.LocationsSystem.District;
import com.acme.studenthome.domain.model.PropertiesSystem.Property;
import com.acme.studenthome.domain.model.UserAccountSystem.LandLord;
import com.acme.studenthome.domain.repository.LocationsSystemRepository.DistrictRepository;
import com.acme.studenthome.domain.repository.PropertiesSystemRepository.PropertyRepository;
import com.acme.studenthome.domain.repository.PropertiesSystemRepository.ServiceRepository;
import com.acme.studenthome.domain.repository.UserAccountSystemRepository.LandLordRepository;
import com.acme.studenthome.domain.service.PropertiesSystemService.PropertyService;
import com.acme.studenthome.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private LandLordRepository landLordRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public Page<Property> getAllProperties(Pageable pageable) {
        return propertyRepository.findAll(pageable);
    }

    @Override
    public Property createProperty(Long landLordId, Long districtId, Property property) {
        LandLord landLord = landLordRepository.findById(landLordId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("LandLord", "Id", landLordId));
        District district = districtRepository.findById(districtId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("District", "Id", districtId));
        property.setDistrict(district);
        property.setLandLord(landLord);
        return propertyRepository.save(property);
    }

    @Override
    public Property getPropertyById(Long propertyId) {
        return propertyRepository.findById(propertyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Property", "Id", propertyId));
    }

    @Override
    public Property updateProperty(Long landLordId, Long propertyId, Long districtId, Property propertyRequest) {
        if (!landLordRepository.existsById(landLordId))
            throw new ResourceNotFoundException("LandLord", "Id", landLordId);
        District district = districtRepository.findById(districtId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("District", "Id", districtId));
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Property", "Id", propertyId));
        property.setTitle(propertyRequest.getTitle());
        property.setDescription(propertyRequest.getDescription());
        property.setActive(propertyRequest.getActive());
        property.setCost(propertyRequest.getCost());
        property.setRooms(propertyRequest.getRooms());
        property.setSize(propertyRequest.getSize());
        property.setAddress(propertyRequest.getAddress());
        property.setDistrict(district);
        return propertyRepository.save(property);
    }

    @Override
    public ResponseEntity<?> deleteProperty(Long landLordId, Long propertyId) {
        if (!landLordRepository.existsById(landLordId))
            throw new ResourceNotFoundException("LandLord", "Id", landLordId);
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Property", "Id", propertyId));
        return ResponseEntity.ok().build();
    }

    @Override
    public Property assignPropertyService(Long propertyId, Long serviceId) {
        com.acme.studenthome.domain.model.PropertiesSystem.Service service = serviceRepository.findById(serviceId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Service", "Id", serviceId));
        return propertyRepository.findById(propertyId)
                .map(property -> propertyRepository.save(property.addService(service)))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Property", "Id", propertyId));
    }

    @Override
    public Property unAssignPropertyService(Long propertyId, Long serviceId) {
        com.acme.studenthome.domain.model.PropertiesSystem.Service service = serviceRepository.findById(serviceId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Service", "Id", serviceId));
        return propertyRepository.findById(propertyId)
                .map(property -> propertyRepository.save(property.removeService(service)))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Property", "Id", propertyId));
    }

    @Override
    public Page<Property> getAllPropertiesByServiceId(Long serviceId, Pageable pageable) {
        return serviceRepository.findById(serviceId)
                .map(service -> {
                    List<Property> properties = service.getProperties();
                    return new PageImpl<>(properties, pageable, properties.size());
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException("Service", "Id", serviceId));
    }

    @Override
    public Page<Property> getAllPropertiesByLandLordId(Long landLordId, Pageable pageable) {
        return propertyRepository.findByLandLordId(landLordId, pageable);
    }

    @Override
    public Page<Property> getAllActiveProperties(Boolean active, Pageable pageable) {
        return propertyRepository.findByActive(active, pageable);
    }
}
