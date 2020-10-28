package com.acme.studenthome.service.PropertiesSystemService;

import com.acme.studenthome.domain.repository.PropertiesSystemRepository.PropertyRepository;
import com.acme.studenthome.domain.repository.PropertiesSystemRepository.ServiceRepository;
import com.acme.studenthome.domain.service.PropertiesSystemService.ServiceService;
import com.acme.studenthome.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public Page<com.acme.studenthome.domain.model.PropertiesSystem.Service> getAllService(Pageable pageable) {
        return serviceRepository.findAll(pageable);
    }

    @Override
    public com.acme.studenthome.domain.model.PropertiesSystem.Service getServiceById(Long serviceId) {
        return serviceRepository.findById(serviceId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Service", "Id", serviceId));
    }

    @Override
    public Page<com.acme.studenthome.domain.model.PropertiesSystem.Service> getAllServicesByPropertyId(Long propertyId, Pageable pageable) {
        return propertyRepository.findById(propertyId)
                .map(property -> {
                    List<com.acme.studenthome.domain.model.PropertiesSystem.Service> services = property.getServices();
                    return new PageImpl<>(services, pageable, services.size());
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException("Property", "Id", propertyId));
    }
}
