package com.acme.studenthome.domain.repository.UserAccountSystemRepository.StudentSystemRepository;

import com.acme.studenthome.domain.model.UserAccountSystem.StudentSystem.EducationCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface EducationCenterRepository extends JpaRepository<EducationCenter, Long> {
    Optional<EducationCenter> findById(Long educationCenterId);
}
