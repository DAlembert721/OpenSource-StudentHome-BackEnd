package com.acme.studenthome.controller.UserAccountSystemController.StudentSystemController;

import com.acme.studenthome.domain.model.UserAccountSystem.StudentSystem.Student;
import com.acme.studenthome.domain.service.UserAccountSystemService.StudentSystemService.StudentService;
import com.acme.studenthome.resource.UserAccountSystemResource.StudentSystemResource.SaveStudentResource;
import com.acme.studenthome.resource.UserAccountSystemResource.StudentSystemResource.StudentResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class StudentsController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private StudentService studentService;

    @GetMapping("/students/{studentId}")
    public StudentResource getStudentById(@PathVariable(name = "studentId") Long studentId) {
        return convertToResource(studentService.getStudentById(studentId));
    }

    private Student convertToEntity(SaveStudentResource resource) {
        return mapper.map(resource, Student.class);
    }

    private StudentResource convertToResource(Student entity) {
        return mapper.map(entity, StudentResource.class);
    }
}
