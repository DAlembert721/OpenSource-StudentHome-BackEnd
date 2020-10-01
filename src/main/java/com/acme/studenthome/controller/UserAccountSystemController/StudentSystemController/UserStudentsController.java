package com.acme.studenthome.controller.UserAccountSystemController.StudentSystemController;

import com.acme.studenthome.domain.model.UserAccountSystem.Account;
import com.acme.studenthome.domain.model.UserAccountSystem.StudentSystem.Student;
import com.acme.studenthome.domain.service.UserAccountSystem.StudentSystemService.StudentService;
import com.acme.studenthome.resource.UserAccountSystem.AccountResource;
import com.acme.studenthome.resource.UserAccountSystem.SaveAccountResource;
import com.acme.studenthome.resource.UserAccountSystem.StudentSystemResource.SaveStudentResource;
import com.acme.studenthome.resource.UserAccountSystem.StudentSystemResource.StudentResource;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/users/{userId}/students")
    public StudentResource createStudent(@PathVariable(name = "userId") Long userId, @Valid @RequestBody SaveStudentResource studentRequest) {
        Student student = convertToEntity(studentRequest);
        return convertToResource(studentService.createStudent(userId, studentRequest.getEducationCenterId(), studentRequest.getDistrictId(), student));
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
