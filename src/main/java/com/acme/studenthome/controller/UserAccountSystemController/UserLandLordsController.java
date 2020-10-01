package com.acme.studenthome.controller.UserAccountSystemController;

import com.acme.studenthome.domain.model.UserAccountSystem.LandLord;
import com.acme.studenthome.domain.service.UserAccountSystem.LandLordService;
import com.acme.studenthome.resource.UserAccountSystem.LandLordResource;
import com.acme.studenthome.resource.UserAccountSystem.SaveLandLordResource;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserLandLordsController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private LandLordService landLordService;

    @PostMapping("/users/{userId}/landlords")
    public LandLordResource createLandlord(@PathVariable Long userId, @Valid @RequestBody SaveLandLordResource resource) {
        LandLord landLord = convertToEntity(resource);
        return convertToResource(landLordService.createLandLord(userId, resource.getSubscriptionId(), landLord));
    }

    private LandLord convertToEntity(SaveLandLordResource resource) {
        return mapper.map(resource, LandLord.class);
    }

    private LandLordResource convertToResource(LandLord entity) {
        return mapper.map(entity, LandLordResource.class);
    }

    @PostConstruct
    public void init(){
        mapper.addMappings(new PropertyMap<LandLord, LandLordResource>() {
            @Override
            protected void configure() {
                map().setSubscriptionName(source.getSubscription().getName());
            }
        });
    }

}
