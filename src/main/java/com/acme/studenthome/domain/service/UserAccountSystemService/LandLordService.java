package com.acme.studenthome.domain.service.UserAccountSystemService;

import com.acme.studenthome.domain.model.UserAccountSystem.LandLord;
import com.acme.studenthome.domain.model.UserAccountSystem.StudentSystem.Student;
import org.springframework.http.ResponseEntity;

public interface LandLordService {

    LandLord getLandLordByIdAndUserId(Long landLordId, Long userId);

    LandLord createLandLord(Long userId, Long subscriptionId, LandLord landLord);

    LandLord updateLandLord(Long userId, Long landLordId,LandLord landLordRequest);

    ResponseEntity<?> deleteLandLord(Long userId, Long landLordId);

}
