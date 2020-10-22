package com.acme.studenthome.controller.UserAccountSystemController.StudentSystemController;

import com.acme.studenthome.domain.model.UserAccountSystem.StudentSystem.Student;
import com.acme.studenthome.domain.service.UserAccountSystemService.StudentSystemService.StudentService;
import com.acme.studenthome.resource.UserAccountSystemResource.StudentSystemResource.SaveStudentResource;
import com.acme.studenthome.resource.UserAccountSystemResource.StudentSystemResource.StudentResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserStudentsController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private StudentService studentService;

    @Operation(summary = "Get Student",
            description = "Get a Student",
            tags = "users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Student founded",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/users/{userId}/students/{studentId}")
    public StudentResource getLandlordByIdAndUserId(@PathVariable(name = "studentId") Long studentId,
                                                    @PathVariable(name = "userId") Long userId) {
        return convertToResource(studentService.getStudentByIdAndUserId(studentId, userId));
    }

    @Operation(summary = "Post Student",
            description = "Create a Student",
            tags = "users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Student created",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/users/{userId}/students")
    public StudentResource createStudent(@PathVariable(name = "userId") Long userId, @Valid @RequestBody SaveStudentResource studentRequest) {
        Student student = convertToEntity(studentRequest);
        return convertToResource(studentService.createStudent(userId, studentRequest.getEducationCenterId(), studentRequest.getDistrictId(), student));
    }

    @Operation(summary = "Put Student",
            description = "Update a Student",
            tags = "users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Student information updated",
                    content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/users/{userId}/students/{studentId}")
    public StudentResource updateLandlord(@PathVariable(name = "userId") Long userId,
                                           @PathVariable(name = "studentId") Long studentId,
                                           @Valid @RequestBody SaveStudentResource resource){
        Student student = convertToEntity(resource);
        return convertToResource(studentService.updateStudent(userId, studentId, student));
    }

    @Operation(summary = "Delete Student",
            description = "Delete a Student",
            tags = "users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Student deleted",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/users/{userId}/students/{studentId}")
    public ResponseEntity<?> deleteResponse(@PathVariable(name = "userId") Long userId, @PathVariable(name="studentId") Long studentId) {
        return studentService.deleteStudent(userId, studentId);
    }

    private Student convertToEntity(SaveStudentResource resource) {
        return mapper.map(resource, Student.class);
    }

    private StudentResource convertToResource(Student entity) {

        return mapper.map(entity, StudentResource.class);
    }

    @PostConstruct
    public void init(){
        mapper.addMappings(new PropertyMap<Student, StudentResource>() {
            @Override
            protected void configure() {
                map().setDistrictName(source.getDistrict().getName());
                map().setEducationCenterName(source.getEducationCenter().getName());
            }
        });
    }
}
