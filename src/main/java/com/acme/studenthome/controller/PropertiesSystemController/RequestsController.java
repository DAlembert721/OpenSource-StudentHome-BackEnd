package com.acme.studenthome.controller.PropertiesSystemController;

import com.acme.studenthome.domain.model.PropertiesSystem.Request;
import com.acme.studenthome.domain.service.PropertiesSystemService.RequestService;
import com.acme.studenthome.resource.PropertiesSystemResource.RequestResource;
import com.acme.studenthome.resource.PropertiesSystemResource.SaveRequestResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class RequestsController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RequestService requestService;

    @Operation(summary = "Create Request", description = "Create a new request", tags = {"students"})
    @PostMapping("/students/{studentId}/properties/{propertyId}/requests")
    public RequestResource createRequest(@PathVariable(name = "studentId") Long studentId,
                                                 @PathVariable(name = "propertyId") Long propertyId,
                                                 @RequestBody @Valid SaveRequestResource resource){
        Request request = convertToEntity(resource);
        return convertToResource(requestService.createRequest(studentId, propertyId, request));
    }


    @Operation(summary = "Update Request", description = "Update a Request", tags = {"requests"})
    @PutMapping("/requests/{requestId}")
    public RequestResource updateRequest(@PathVariable(name = "requestId") Long requestId,
                                                 @RequestBody @Valid SaveRequestResource resource){
        Request request = convertToEntity(resource);
        return convertToResource(requestService.updateRequest(requestId, request));
    }

    @Operation(summary = "Delete Request", description = "Delete a Request", tags = {"requests"})
    @DeleteMapping("/requests/{requestId}")
    public ResponseEntity<?> deleteRequest(@PathVariable(name = "requestId") Long requestId){
        return requestService.deleteRequest(requestId);
    }



    private Request convertToEntity(SaveRequestResource resource) {
        return mapper.map(resource, Request.class);
    }

    private RequestResource convertToResource(Request entity) {
        return mapper.map(entity, RequestResource.class);
    }
}
