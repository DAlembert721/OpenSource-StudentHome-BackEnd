package com.acme.studenthome.domain.repository.PropertiesSystemRepository;

import com.acme.studenthome.domain.model.PropertiesSystem.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Page<Payment> findByContractId(Long contractId, Pageable pageable);
}
