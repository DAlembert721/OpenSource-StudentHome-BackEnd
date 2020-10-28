package com.acme.studenthome.service.UserAccountSystem.StudentSystemService;

import com.acme.studenthome.domain.model.LocationsSystem.District;
import com.acme.studenthome.domain.model.UserAccountSystem.StudentSystem.EducationCenter;
import com.acme.studenthome.domain.model.UserAccountSystem.StudentSystem.Student;
import com.acme.studenthome.domain.model.UserAccountSystem.User;
import com.acme.studenthome.domain.repository.LocationsSystemRepository.DistrictRepository;
import com.acme.studenthome.domain.repository.UserAccountSystemRepository.StudentSystemRepository.EducationCenterRepository;
import com.acme.studenthome.domain.repository.UserAccountSystemRepository.StudentSystemRepository.StudentRepository;
import com.acme.studenthome.domain.repository.UserAccountSystemRepository.UserRepository;
import com.acme.studenthome.domain.service.UserAccountSystemService.StudentSystemService.StudentService;
import com.acme.studenthome.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private EducationCenterRepository educationCenterRepository;

    @Override
    public Student getStudentByIdAndUserId(Long studentId, Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User", "Id", userId);
        }
        return studentRepository.findByIdAndUserId(studentId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "Id", studentId));
    }

    @Override
    public Student createStudent(Long userId, Long educationCenterId, Long districtId, Student student) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

        EducationCenter educationCenter = educationCenterRepository.findById(educationCenterId)
                .orElseThrow(() -> new ResourceNotFoundException("EducationCenter", "Id", educationCenterId));

        District district = districtRepository.findById(districtId)
                .orElseThrow(() -> new ResourceNotFoundException("District", "Id", districtId));

        student.setUser(user);
        student.setEducationCenter(educationCenter);
        student.setDistrict(district);

        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Long userId, Long studentId, Student studentRequest) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User", "Id", userId);
        }
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "Id", studentId));
        student.setDni(studentRequest.getDni());
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setPhone(studentRequest.getPhone());
        student.setImage(studentRequest.getImage());
        student.setDistrict(studentRequest.getDistrict());
        return studentRepository.save(student);
    }

    @Override
    public ResponseEntity<?> deleteStudent(Long userId, Long studentId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User", "Id", userId);
        }
        Student student = studentRepository.findByIdAndUserId(studentId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "Id", studentId));
        studentRepository.delete(student);
        return ResponseEntity.ok().build();
    }
}
