package com.acme.studenthome.controller.UserAccountSystemController.StudentSystemController;

import com.acme.studenthome.domain.model.UserAccountSystem.StudentSystem.StudentOpinion;
import com.acme.studenthome.domain.service.UserAccountSystemService.StudentSystemService.StudentOpinionService;
import com.acme.studenthome.resource.UserAccountSystemResource.StudentSystemResource.SaveStudentOpinionResource;
import com.acme.studenthome.resource.UserAccountSystemResource.StudentSystemResource.StudentOpinionResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class StudentOpinionController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private StudentOpinionService studentOpinionService;

    @GetMapping("studentOpinions")
    public Page<StudentOpinionResource> getAllStudentOpinion( Pageable pageable) {
        Page<StudentOpinion> studentOpinionPage =
                studentOpinionService.getAllStudentOpinions(pageable);
        List<StudentOpinionResource> resources = studentOpinionPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("landlords/{landLordId}/studentOpinions")
    public Page<StudentOpinionResource> getAllStudentOpinionByLandLord(
            @PathVariable Long landLordId, Pageable pageable) {
        Page<StudentOpinion> studentOpinionPage =
                studentOpinionService.getAllStudentOpinionsByLandlordId(landLordId, pageable);
        List<StudentOpinionResource> resources = studentOpinionPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    @GetMapping("students/{studentId}/studentOpinions")
    public Page<StudentOpinionResource> getAllStudentOpinionByStudent(
            @PathVariable Long studentId, Pageable pageable) {
        Page<StudentOpinion> studentOpinionPage =
                studentOpinionService.getAllStudentOpinionsByStudentId(studentId, pageable);
        List<StudentOpinionResource> resources = studentOpinionPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    @GetMapping("landlords/{landLordId}/students/{studentId}/studentOpinions")
    public Page<StudentOpinionResource> getAllStudentOpinionByLandLordAndStudent(
            @PathVariable (name = "landLordId") Long landLordId,
            @PathVariable (name = "studentId") Long studentId, Pageable pageable) {
        Page<StudentOpinion> studentOpinionPage = studentOpinionService.
                getAllStudentOpinionsByStudentIdAndLandlordId(studentId,landLordId, pageable);
        List<StudentOpinionResource> resources = studentOpinionPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("landlords/{landLordId}/students/{studentId}/studentOpinions/{studentOpinionsId}")
    public StudentOpinionResource getStudentOpinionsByIdAndlandLordAndStudent(
            @PathVariable (name = "landLordId") Long landLordId,
            @PathVariable (name = "studentId") Long studentId,
            @PathVariable(name = "studentOpinionsId") Long studentOpinionsId) {
        return convertToResource(studentOpinionService.
                getStudentOpinionByIdAndStudentIdAndLandlordId(
                        studentOpinionsId,studentId,landLordId));
    }

    @PostMapping("landlords/{landLordId}/students/{studentId}/studentOpinions")
    public StudentOpinionResource createStudentOpinion(
            @PathVariable (name = "landLordId") Long landLordId,
            @PathVariable (name = "studentId") Long studentId,
            @Valid @RequestBody SaveStudentOpinionResource resource) {
        return convertToResource(studentOpinionService.createStudentOpinion(
                studentId,landLordId, convertToEntity(resource)));
    }

    @PutMapping("landlords/{landLordId}/students/{studentId}/studentOpinions/{studentOpinionsId}")
    public StudentOpinionResource updateStudentOpinion(
            @PathVariable (name = "landLordId") Long landLordId,
            @PathVariable (name = "studentId") Long studentId,
            @PathVariable(name = "studentOpinionsId") Long studentOpinionsId,
            @Valid @RequestBody SaveStudentOpinionResource resource) {
        return convertToResource(studentOpinionService.updateStudentOpinion(
                studentOpinionsId,studentId,landLordId,convertToEntity(resource)));
    }

    @DeleteMapping("landlords/{landLordId}/students/{studentId}/studentOpinions/{studentOpinionsId}")
    public ResponseEntity<?> deleteStudentOpinion(
            @PathVariable (name = "landLordId") Long landLordId,
            @PathVariable (name = "studentId") Long studentId,
            @PathVariable(name = "studentOpinionsId") Long studentOpinionsId) {
        return studentOpinionService.deleteStudentOpinion(studentOpinionsId,studentId,landLordId);
    }

    private StudentOpinion convertToEntity(SaveStudentOpinionResource resource) {
        return mapper.map(resource, StudentOpinion.class);
    }

    private StudentOpinionResource convertToResource(StudentOpinion entity) {
        return mapper.map(entity, StudentOpinionResource.class);
    }

}
