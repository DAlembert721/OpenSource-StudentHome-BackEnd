package com.acme.studenthome.domain.service.UserAccountSystemService.StudentSystemService;

import com.acme.studenthome.domain.model.UserAccountSystem.StudentSystem.EducationCenter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationCenterService {

    EducationCenter getEducationCenterById(Long educationCenterId);

    EducationCenter createEducationCenter(Long districtId, EducationCenter educationCenter);

    EducationCenter updateEducationCenter(Long educationCenterId, Long districtId, EducationCenter educationCenterRequest);

    ResponseEntity<?> deleteEducationCenter(Long educationCenterId);

}
