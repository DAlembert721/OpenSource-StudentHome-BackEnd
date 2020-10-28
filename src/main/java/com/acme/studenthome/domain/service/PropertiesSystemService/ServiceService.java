package com.acme.studenthome.domain.service.PropertiesSystemService;

import com.acme.studenthome.domain.model.PropertiesSystem.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServiceService {
    Page<Service> getAllService(Pageable pageable);
    Service getServiceById(Long serviceId);
    Page<Service> getAllServicesByPropertyId(Long propertyId, Pageable pageable);
}
