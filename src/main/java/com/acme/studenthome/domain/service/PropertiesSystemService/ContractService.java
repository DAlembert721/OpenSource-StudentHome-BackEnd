package com.acme.studenthome.domain.service.PropertiesSystemService;

import com.acme.studenthome.domain.model.PropertiesSystem.Contract;
import com.acme.studenthome.domain.model.PropertiesSystem.ContractStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface ContractService {
    Contract createContract(Long studentId, Long propertyId, Contract contract);
    Page<Contract> getAllContractsByPropertyId(Long propertyId, Pageable pageable);
    Page<Contract> getAllContractsByStudentId(Long studentId, Pageable pageable);
    Contract updateContract(Long contractId, Contract resource);
    ResponseEntity<?> deleteContract(Long contractId);
    Contract updateStateOfContract(Long contractId, ContractStatus state);
}
