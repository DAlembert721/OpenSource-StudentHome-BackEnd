package com.acme.studenthome.service.PropertiesSystemService;

import com.acme.studenthome.domain.model.PropertiesSystem.Property;
import com.acme.studenthome.domain.model.PropertiesSystem.Request;
import com.acme.studenthome.domain.model.UserAccountSystem.StudentSystem.Student;
import com.acme.studenthome.domain.repository.PropertiesSystemRepository.PropertyRepository;
import com.acme.studenthome.domain.repository.PropertiesSystemRepository.RequestRepository;
import com.acme.studenthome.domain.repository.UserAccountSystemRepository.StudentSystemRepository.StudentRepository;
import com.acme.studenthome.domain.service.PropertiesSystemService.RequestService;
import com.acme.studenthome.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private RequestRepository requestRepository;

    @Override
    public Request createRequest(Long studentId, Long propertyId, Request request) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student", "Id", studentId));
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Property", "Id", propertyId));
        request.setStudent(student);
        request.setProperty(property);
        return  requestRepository.save(request);
    }

    @Override
    public Page<Request> getAllRequestsByPropertyId(Long propertyId, Pageable pageable) {

        if(!propertyRepository.existsById(propertyId))
            throw new ResourceNotFoundException("Property", "Id", propertyId);
        return requestRepository.findByPropertyId(propertyId, pageable);
    }

    @Override
    public Page<Request> getAllRequestsByStudentIdId(Long studentId, Pageable pageable) {
        if(!studentRepository.existsById(studentId))
            throw new ResourceNotFoundException("Student", "Id", studentId);
        return requestRepository.findByStudentId(studentId, pageable);
    }

    @Override
    public Request updateRequest(Long requestId, Request resource) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Request", "Id", requestId));
        request.setContent(resource.getContent());
        request.setState(resource.getState());
        return requestRepository.save(request);
    }

    @Override
    public ResponseEntity<?> deleteRequest(Long requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Request", "Id", requestId));
        requestRepository.delete(request);
        return ResponseEntity.ok().build();
    }
}
