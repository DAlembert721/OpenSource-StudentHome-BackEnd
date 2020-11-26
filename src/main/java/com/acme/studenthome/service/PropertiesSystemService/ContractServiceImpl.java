package com.acme.studenthome.service.PropertiesSystemService;

import com.acme.studenthome.domain.model.PropertiesSystem.Contract;
import com.acme.studenthome.domain.model.PropertiesSystem.ContractStatus;
import com.acme.studenthome.domain.model.PropertiesSystem.Property;
import com.acme.studenthome.domain.model.UserAccountSystem.StudentSystem.Student;
import com.acme.studenthome.domain.repository.PropertiesSystemRepository.ContractRepository;
import com.acme.studenthome.domain.repository.PropertiesSystemRepository.PropertyRepository;
import com.acme.studenthome.domain.repository.UserAccountSystemRepository.StudentSystemRepository.StudentRepository;
import com.acme.studenthome.domain.service.PropertiesSystemService.ContractService;
import com.acme.studenthome.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private ContractRepository contractRepository;

    @Override
    public Contract createContract(Long studentId, Long propertyId, Contract contract) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student", "Id", studentId));
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Property", "Id", propertyId));
        contract.setStudent(student);
        contract.setProperty(property);
        contract.setState(ContractStatus.UNRESOLVED);
        return  contractRepository.save(contract);
    }

    @Override
    public Page<Contract> getAllContractsByPropertyId(Long propertyId, Pageable pageable) {
        if(!propertyRepository.existsById(propertyId))
            throw new ResourceNotFoundException("Property", "Id", propertyId);
        return contractRepository.findByPropertyId(propertyId, pageable);

    }

    @Override
    public Page<Contract> getAllContractsByStudentId(Long studentId, Pageable pageable) {
        if(!studentRepository.existsById(studentId))
            throw new ResourceNotFoundException("Student", "Id", studentId);
        return contractRepository.findByStudentId(studentId, pageable);
    }

    @Override
    public Contract updateContract(Long contractId, Contract resource) {

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Contract", "Id", contractId));
        contract.setAmount(resource.getAmount());
        contract.setState(resource.getState());
        contract.setDescription(resource.getDescription());
        return contractRepository.save(contract);
    }

    @Override
    public ResponseEntity<?> deleteContract(Long contractId) {

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Contract", "Id", contractId));
        contractRepository.delete(contract);
        return ResponseEntity.ok().build();
    }

    @Override
    public Contract updateStateOfContract(Long contractId, ContractStatus state) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Contract", "Id", contractId));
        contract.setState(state);
        return contractRepository.save(contract);
    }
}
