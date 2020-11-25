package com.acme.studenthome.domain.repository.PropertiesSystemRepository;

import com.acme.studenthome.domain.model.PropertiesSystem.PropertyImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyImageRepository extends JpaRepository<PropertyImage, Long> {
    Page<PropertyImage> findByPropertyId(Long propertyId, Pageable pageable);
}
