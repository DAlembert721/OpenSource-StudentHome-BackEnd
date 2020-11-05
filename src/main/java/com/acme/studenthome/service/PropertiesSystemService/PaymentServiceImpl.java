package com.acme.studenthome.service.PropertiesSystemService;

import com.acme.studenthome.domain.model.PropertiesSystem.Payment;
import com.acme.studenthome.domain.model.PropertiesSystem.Property;
import com.acme.studenthome.domain.model.UserAccountSystem.StudentSystem.Student;
import com.acme.studenthome.domain.repository.PropertiesSystemRepository.PaymentRepository;
import com.acme.studenthome.domain.repository.PropertiesSystemRepository.PropertyRepository;
import com.acme.studenthome.domain.repository.UserAccountSystemRepository.StudentSystemRepository.StudentRepository;
import com.acme.studenthome.domain.service.PropertiesSystemService.PaymentService;
import com.acme.studenthome.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Payment createPayment(Long propertyId, Long studentId, Payment payment) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Property", "Id", propertyId));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student", "Id", studentId));
        payment.setProperty(property);
        payment.setStudent(student);
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Payment", "Id", paymentId));
    }

    @Override
    public Payment updatePayment(Long paymentId, Payment resource) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Payment", "Id", paymentId));
        payment.setAmount(resource.getAmount());
        payment.setChecked(resource.getChecked());
        payment.setImage(resource.getImage());
        return paymentRepository.save(payment);
    }

    @Override
    public ResponseEntity<?> deletePayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Payment", "Id", paymentId));
        paymentRepository.delete(payment);
        return ResponseEntity.ok().build();
    }

    @Override
    public Page<Payment> getAllPaymentsByPropertyId(Long propertyId, Pageable pageable) {
        if (!propertyRepository.existsById(propertyId))
            throw new ResourceNotFoundException("Property", "Id", propertyId);
        return paymentRepository.findByPropertyId(propertyId, pageable);
    }

    @Override
    public Page<Payment> getAllPaymentsByStudentId(Long studentId, Pageable pageable) {
        if (!studentRepository.existsById(studentId))
            throw new ResourceNotFoundException("Student", "Id", studentId);
        return paymentRepository.findByStudentId(studentId, pageable);
    }
}
