package com.acme.studenthome;

import com.acme.studenthome.domain.model.UserAccountSystem.StudentSystem.StudentOpinion;
import com.acme.studenthome.domain.repository.UserAccountSystemRepository.LandLordRepository;
import com.acme.studenthome.domain.repository.UserAccountSystemRepository.StudentSystemRepository.StudentOpinionRepository;
import com.acme.studenthome.domain.repository.UserAccountSystemRepository.StudentSystemRepository.StudentRepository;
import com.acme.studenthome.domain.service.UserAccountSystemService.StudentSystemService.StudentOpinionService;
import com.acme.studenthome.exception.ResourceNotFoundException;
import com.acme.studenthome.service.UserAccountSystem.StudentSystemService.StudentOpinionServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import java.util.Optional;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class StudentOpinionImplTest {

    @MockBean
    private LandLordRepository landLordRepository;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private StudentOpinionRepository studentOpinionRepository;

    @Autowired
    private StudentOpinionService studentOpinionService;

    @TestConfiguration
    static class PostServiceImplTestConfiguration {
        @Bean
        public StudentOpinionService studentOpinionService() {
            return new StudentOpinionServiceImpl();
        }
    }

    @Test
    @DisplayName("When Get StudentOpinions By Valid ID and" +
            "Valid LandLord ID And Valid Student ID Then Returns Post")
    public void whenGetStudentOpinionsByValidIdAndLandLordIDAndValidStudentIDThenReturnsPost() {
        // Arrange
        Long landlordId = 1L;
        Long studentId = 1L;
        Long studentOpinionId = 1L;
        StudentOpinion studentOpinion = new StudentOpinion();
        studentOpinion.setId(studentOpinionId);
        studentOpinion.setContent("Ha pagado a tiempo, pero a veces hacía bulla");
        studentOpinion.setScore((long)500);
        studentOpinion.setStudent(studentRepository.findById(studentId).orElse(null));
        studentOpinion.setLandLord(landLordRepository.findById(landlordId).orElse(null));

        when(studentOpinionRepository
                .findByIdAndStudentIdAndLandLordId(studentOpinionId,studentId,landlordId))
                .thenReturn(Optional.of(studentOpinion));

        // Act
        StudentOpinion foundStudentOpinion = studentOpinionService
                .getStudentOpinionByIdAndStudentIdAndLandlordId(
                        studentOpinionId,studentId,landlordId);

        // Assert
        assertThat(foundStudentOpinion.getStudent().getId()).isEqualTo(studentId);
        assertThat(foundStudentOpinion.getLandLord().getId()).isEqualTo(landlordId);
    }

    @Test
    @DisplayName("When Get StudentOpinions By Invalid LandLord ID" +
            "And Invalid Student ID Then Returns ResourceNotFoundException")
    public void whenGetPostByTitleWithInvalidTitleThenReturnsResourceNotFoundException() {
        // Arrange
        Long landlordId = 1L;
        Long studentId = 1L;
        Long studentOpinionId = 1L;
        when(studentOpinionRepository
                .findByIdAndStudentIdAndLandLordId(studentOpinionId,studentId,landlordId))
                .thenReturn(Optional.empty());
        String expectedMessage = "StudentOpinion not found with Id " + studentOpinionId +
                " and StudentId " + studentId + " and LandlordId " + landlordId;

        // Act
        Throwable exception = catchThrowable(() -> {
            StudentOpinion foundStudentOpinion = studentOpinionService
                    .getStudentOpinionByIdAndStudentIdAndLandlordId(
                            studentOpinionId,studentId,landlordId);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
}
