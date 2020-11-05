package com.acme.studenthome.controller.PropertiesSystemController;

import com.acme.studenthome.domain.model.PropertiesSystem.Payment;
import com.acme.studenthome.domain.service.PropertiesSystemService.PaymentService;
import com.acme.studenthome.resource.PropertiesSystemResource.PaymentResource;
import com.acme.studenthome.resource.PropertiesSystemResource.SavePaymentResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PropertyPaymentsController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PaymentService paymentService;

    @Operation(summary = "Get Payments By Property Id",
            description = "Get All Payments given a Property Id",
            tags = "properties")
    @GetMapping("/properties/{propertyId}/payments")
    public Page<PaymentResource> getAllPaymentsByPropertyId(@PathVariable(name = "propertyId") Long propertyId, Pageable pageable) {
        Page<Payment> paymentPage = paymentService.getAllPaymentsByPropertyId(propertyId, pageable);
        List<PaymentResource> paymentResources = paymentPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(paymentResources, pageable, paymentResources.size());
    }

    private Payment convertToEntity(SavePaymentResource resource) {
        return mapper.map(resource, Payment.class);
    }

    private PaymentResource convertToResource(Payment entity) {
        return mapper.map(entity, PaymentResource.class);
    }
}
