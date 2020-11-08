package com.acme.studenthome.domain.service.PropertiesSystemService;

import com.acme.studenthome.domain.model.PropertiesSystem.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface RequestService {
    Request createRequest(Long studentId, Long propertyId, Request request);
    Page<Request> getAllRequestsByPropertyId(Long propertyId, Pageable pageable);
    Page<Request> getAllRequestsByStudentIdId(Long studentId, Pageable pageable);
    Request updateRequest(Long requestId, Request resource);
    ResponseEntity<?> deleteRequest(Long requestId);
}
