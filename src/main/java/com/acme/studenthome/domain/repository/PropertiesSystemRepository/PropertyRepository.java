package com.acme.studenthome.domain.repository.PropertiesSystemRepository;

import com.acme.studenthome.domain.model.PropertiesSystem.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    Page<Property> findByLandLordId(Long landLordId, Pageable pageable);
}
