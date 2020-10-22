package com.acme.studenthome.domain.repository.PropertiesSystemRepository;

import com.acme.studenthome.domain.model.PropertiesSystem.PropertyComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyCommentRepository extends JpaRepository<PropertyComment, Long> {
    Page<PropertyComment> findByStudentId(Long studentId, Pageable pageable);
    Page<PropertyComment> findByPropertyId(Long propertyId, Pageable pageable);
}
