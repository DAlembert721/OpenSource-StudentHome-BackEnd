package com.acme.studenthome.domain.service.UserAccountSystemService.StudentSystemService;

import com.acme.studenthome.domain.model.UserAccountSystem.Account;
import com.acme.studenthome.domain.model.UserAccountSystem.StudentSystem.StudentOpinion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface StudentOpinionService {

    Page<StudentOpinion> getAllStudentOpinionsByStudentIdAndLandlordId (Long studentId, Long landlordId, Pageable pageable);
    Page<StudentOpinion> getAllStudentOpinionsByStudentId (Long studentId, Pageable pageable);
    Page<StudentOpinion> getAllStudentOpinionsByLandlordId (Long landlordId, Pageable pageable);
    Page<StudentOpinion> getAllStudentOpinions (Pageable pageable);

    StudentOpinion createStudentOpinion(Long studentId, Long landlordId, StudentOpinion studentOpinion);
    StudentOpinion getStudentOpinionByIdAndStudentIdAndLandlordId(Long studentOpinionId,Long studentId, Long landlordId);
    StudentOpinion updateStudentOpinion(Long studentOpinionId, Long studentId, Long landlordId, StudentOpinion studentOpinionRequest);
    ResponseEntity<?> deleteStudentOpinion(Long studentOpinionId, Long studentId, Long landlordId);
}
