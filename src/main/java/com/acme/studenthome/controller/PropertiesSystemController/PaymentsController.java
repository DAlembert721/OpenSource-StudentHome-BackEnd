package com.acme.studenthome.controller.PropertiesSystemController;

import com.acme.studenthome.domain.model.PropertiesSystem.Payment;
import com.acme.studenthome.domain.service.PropertiesSystemService.PaymentService;
import com.acme.studenthome.resource.PropertiesSystemResource.PaymentResource;
import com.acme.studenthome.resource.PropertiesSystemResource.SavePaymentResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class PaymentsController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PaymentService paymentService;

    @Operation(summary = "Get Payment By Payment Id",
            description = "Get A Payment given a Id",
            tags = "payments")
    @GetMapping("/payments/{paymentId}")
    public PaymentResource getAllPaymentsByPropertyId(@PathVariable(name = "paymentId") Long paymentId) {
        return convertToResource(paymentService.getPaymentById(paymentId));
    }

    @Operation(summary = "Post Payment",
            description = "Post A New Payment",
            tags = "students")
    @PostMapping("/students/{studentId}/properties/{propertyId}/payments")
    public PaymentResource createPayment(
            @PathVariable(name = "studentId") Long studentId,
            @PathVariable(name = "propertyId") Long propertyId,
            @RequestBody @Valid SavePaymentResource paymentResource) {
        Payment payment = convertToEntity(paymentResource);
        return convertToResource(paymentService.createPayment(studentId, propertyId, payment));
    }

    @Operation(summary = "Update Payment By Payment Id",
            description = "Update A Payment given a Id",
            tags = "payments")
    @PutMapping("/payments/{paymentId}")
    public PaymentResource updatePayment(
            @PathVariable(name = "paymentId") Long paymentId,
            @RequestBody @Valid SavePaymentResource paymentResource) {
        Payment payment = convertToEntity(paymentResource);
        return convertToResource(paymentService.updatePayment(paymentId, payment));
    }

    @Operation(summary = "Delete Payment By Payment Id",
            description = "Delete A Payment given a Id",
            tags = "payments")
    @DeleteMapping("/payments/{paymentId}")
    public ResponseEntity<?> deletePaymentById(@PathVariable(name = "paymentId") Long paymentId) {
        return paymentService.deletePayment(paymentId);
    }

    private Payment convertToEntity(SavePaymentResource resource) {
        return mapper.map(resource, Payment.class);
    }

    private PaymentResource convertToResource(Payment entity) {
        return mapper.map(entity, PaymentResource.class);
    }
}
