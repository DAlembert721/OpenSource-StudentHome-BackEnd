package com.acme.studenthome.controller.SubscriptionSystemController;

import com.acme.studenthome.domain.model.SuscriptionsSystem.Subscription;
import com.acme.studenthome.domain.service.SubscriptionSystemService.SubscriptionService;
import com.acme.studenthome.resource.SubscriptionSystemResource.SubscriptionResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SubscriptionController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private SubscriptionService subscriptionService;


    @Operation(summary = "Get Subscriptions",
            description = "Get All Subscriptions by Page",
            tags = "subscriptions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All subscriptions returned",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/subscriptions")
    public Page<SubscriptionResource> getAllSubscriptions(Pageable pageable) {
        Page<Subscription> subscriptionPage = subscriptionService.getAllSubscriptions(pageable);
        List<SubscriptionResource> subscriptionResources = subscriptionPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(subscriptionResources, pageable, subscriptionResources.size());
    }

    private SubscriptionResource convertToResource(Subscription entity) {
        return mapper.map(entity, SubscriptionResource.class);
    }
}
