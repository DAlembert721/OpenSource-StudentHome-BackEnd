package com.acme.studenthome.domain.service.UserAccountSystemService.StudentSystemService;

import com.acme.studenthome.domain.model.UserAccountSystem.StudentSystem.EducationCenter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;



@Repository
public interface EducationCenterService {
    Page<EducationCenter> getAllEducationCenters(Pageable pageable);

    EducationCenter getEducationCenterById(Long educationCenterId);

    EducationCenter createEducationCenter(Long districtId, EducationCenter educationCenter);

    EducationCenter updateEducationCenter(Long educationCenterId, Long districtId, EducationCenter educationCenterRequest);

    ResponseEntity<?> deleteEducationCenter(Long educationCenterId);

}
