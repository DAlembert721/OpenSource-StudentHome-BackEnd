package com.acme.studenthome.domain.repository.UserAccountSystemRepository.StudentSystemRepository;

import com.acme.studenthome.domain.model.UserAccountSystem.StudentSystem.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
   Optional<Student> findByIdAndUserId(Long studentId, Long userId);
}
