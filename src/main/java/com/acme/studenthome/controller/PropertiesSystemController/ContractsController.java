package com.acme.studenthome.controller.PropertiesSystemController;

import com.acme.studenthome.domain.model.PropertiesSystem.Contract;
import com.acme.studenthome.domain.model.PropertiesSystem.EContractStatus;
import com.acme.studenthome.domain.service.PropertiesSystemService.ContractService;
import com.acme.studenthome.resource.PropertiesSystemResource.ContractResource;
import com.acme.studenthome.resource.PropertiesSystemResource.SaveContractResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ContractsController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ContractService contractService;


    @Operation(summary = "Create Contract", description = "Create a new contract", tags = {"students"})
    @PostMapping("/students/{studentId}/properties/{propertyId}/contracts")
    public ContractResource createContracts(@PathVariable(name = "studentId") Long studentId,
                                                 @PathVariable(name = "propertyId") Long propertyId,
                                                 @RequestBody @Valid SaveContractResource resource){
        Contract contract = convertToEntity(resource);
        return convertToResource(contractService.createContract(studentId, propertyId, contract));
    }


    @Operation(summary = "Update Contract", description = "Update a contract", tags = {"contracts"})
    @PutMapping("/contracts/{contractId}")
    public ContractResource updateContract(@PathVariable(name = "contractId") Long contractId,
                                          @RequestBody @Valid SaveContractResource resource){
        Contract contract = convertToEntity(resource);
        return convertToResource(contractService.updateContract(contractId, contract));
    }

    @Operation(summary = "Delete Contract", description = "Delete a contract", tags = {"contracts"})
    @DeleteMapping("/contracts/{contractId}")
    public ResponseEntity<?> deleteContract(@PathVariable(name = "contractId") Long contractId){
        return contractService.deleteContract(contractId);
    }

    @Operation(summary = "Update Contract State",
            description = "Update the State Of A Contract",
            tags = {"contracts"})
    @PutMapping("/contracts/{contractId}/state={state}")
    public ContractResource updateContract(@PathVariable(name = "contractId") Long contractId,
                                         @PathVariable(name = "state") EContractStatus state){
        return convertToResource(contractService.updateStateOfContract(contractId, state));
    }



    private Contract convertToEntity(SaveContractResource resource) {
        return mapper.map(resource, Contract.class);
    }

    private ContractResource convertToResource(Contract entity) {
        return mapper.map(entity, ContractResource.class);
    }

    @PostConstruct
    public void init() {
        mapper.addMappings(new PropertyMap<Contract, ContractResource>() {
            @Override
            protected void configure() {
                map().setFirstNameStudent(source.getStudent().getFirstName());
                map().setLastNameStudent(source.getStudent().getLastName());
                map().setPropertyId(source.getProperty().getId());
                map().setStudentId(source.getStudent().getId());
            }
        });
    }
}
