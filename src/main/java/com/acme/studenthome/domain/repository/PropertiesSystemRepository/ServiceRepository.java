package com.acme.studenthome.domain.repository.PropertiesSystemRepository;

import com.acme.studenthome.domain.model.PropertiesSystem.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
