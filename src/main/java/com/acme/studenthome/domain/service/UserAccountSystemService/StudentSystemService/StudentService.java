package com.acme.studenthome.domain.service.UserAccountSystemService.StudentSystemService;

import com.acme.studenthome.domain.model.UserAccountSystem.StudentSystem.Student;
import org.springframework.http.ResponseEntity;

public interface StudentService {

    Student getStudentByIdAndUserId(Long studentId, Long userId);

    Student createStudent(Long userId, Long educationCenterId, Long districtId,Student student);

    Student updateStudent(Long userId, Long studentId,Student studentRequest);

    ResponseEntity<?> deleteStudent(Long userId, Long studentId);

}
