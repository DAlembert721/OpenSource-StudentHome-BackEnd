package com.acme.studenthome.controller.PropertiesSystemController;

import com.acme.studenthome.domain.model.PropertiesSystem.Contract;
import com.acme.studenthome.domain.service.PropertiesSystemService.ContractService;
import com.acme.studenthome.resource.PropertiesSystemResource.ContractResource;
import com.acme.studenthome.resource.PropertiesSystemResource.SaveContractResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class StudentContractsController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ContractService contractService;

    @Operation(summary = "Get All Contracts By StudentId", description = "Get all contracts for a property given a studentId", tags = {"students"})
    @GetMapping("/students/{studentId}/contracts")
    public Page<ContractResource> getAllContractsByStudentId(
            @PathVariable(name = "studentId") Long studentId
            , Pageable pageable){
        Page<Contract> contractPage = contractService.getAllContractsByStudentId(studentId, pageable);
        List<ContractResource> contractResources = contractPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(contractResources, pageable, contractResources.size());
    }

    private ContractResource convertToResource(Contract entity) {
        return mapper.map(entity, ContractResource.class);
    }
}
