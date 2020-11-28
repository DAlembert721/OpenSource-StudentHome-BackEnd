package com.acme.studenthome.service.UserAccountSystem.StudentSystemService;

import com.acme.studenthome.domain.model.LocationsSystem.District;
import com.acme.studenthome.domain.model.UserAccountSystem.StudentSystem.EducationCenter;
import com.acme.studenthome.domain.repository.LocationsSystemRepository.DistrictRepository;
import com.acme.studenthome.domain.repository.UserAccountSystemRepository.StudentSystemRepository.EducationCenterRepository;
import com.acme.studenthome.domain.service.UserAccountSystemService.StudentSystemService.EducationCenterService;
import com.acme.studenthome.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
public class EducationCenterServiceImpl implements EducationCenterService {

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private EducationCenterRepository educationCenterRepository;

    @Override
    public Page<EducationCenter> getAllEducationCenters(Pageable pageable) {
        return educationCenterRepository.findAll(pageable);
    }

    @Override
    public EducationCenter getEducationCenterById(Long educationCenterId) {

        return educationCenterRepository.findById(educationCenterId)
                .orElseThrow(() -> new ResourceNotFoundException("EducationCenter", "Id", educationCenterId));

    }

    @Override
    public EducationCenter createEducationCenter(Long districtId, EducationCenter educationCenter) {

        District district = districtRepository.findById(districtId)
                .orElseThrow(() -> new ResourceNotFoundException("District", "Id", districtId));

        educationCenter.setDistrict(district);

        return educationCenterRepository.save(educationCenter);

    }

    @Override
    public EducationCenter updateEducationCenter(Long educationCenterId, Long districtId, EducationCenter educationCenterRequest) {

        if (!districtRepository.existsById(districtId)) {
            throw new ResourceNotFoundException("District", "Id", districtId);
        }
        EducationCenter educationCenter = educationCenterRepository.findById(educationCenterId)
                .orElseThrow(() -> new ResourceNotFoundException("EducationCenter", "Id", educationCenterId));
        educationCenter.setAddress(educationCenterRequest.getAddress());
        educationCenter.setName(educationCenterRequest.getName());
        educationCenter.setDistrict(educationCenterRequest.getDistrict());
        return educationCenterRepository.save(educationCenter);

    }

    @Override
    public ResponseEntity<?> deleteEducationCenter(Long educationCenterId) {

        EducationCenter educationCenter = educationCenterRepository.findById(educationCenterId)
                .orElseThrow(() -> new ResourceNotFoundException("EducationCenter", "Id", educationCenterId));
        educationCenterRepository.delete(educationCenter);
        return ResponseEntity.ok().build();

    }
}
