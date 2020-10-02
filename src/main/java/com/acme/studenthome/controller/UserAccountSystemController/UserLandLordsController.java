package com.acme.studenthome.controller.UserAccountSystemController;

import com.acme.studenthome.domain.model.UserAccountSystem.LandLord;
import com.acme.studenthome.domain.service.UserAccountSystemService.LandLordService;
import com.acme.studenthome.resource.UserAccountSystemResource.LandLordResource;
import com.acme.studenthome.resource.UserAccountSystemResource.SaveLandLordResource;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/users/{userId}/landlords/{landLordId}")
    public LandLordResource getLandlordByIdAndUserId(@PathVariable(name = "landLordId") Long landLordId, @PathVariable(name = "userId") Long userId) {
        return convertToResource(landLordService.getLandLordByIdAndUserId(landLordId, userId));
    }

    @PostMapping("/users/{userId}/landlords")
    public LandLordResource createLandlord(@PathVariable Long userId, @Valid @RequestBody SaveLandLordResource resource) {
        LandLord landLord = convertToEntity(resource);
        return convertToResource(landLordService.createLandLord(userId, resource.getSubscriptionId(), landLord));
    }

    @PutMapping("/users/{userId}/landlords/{landLordId}")
    public LandLordResource updateLandlord(@PathVariable(name = "userId") Long userId, @PathVariable(name = "landLorId") Long landLordId, @Valid @RequestBody SaveLandLordResource resource){
        LandLord landLord = convertToEntity(resource);
        return convertToResource(landLordService.updateLandLord(userId, landLordId, landLord));
    }

    @DeleteMapping("/users/{userId}/landlords/{landLordId}")
    public ResponseEntity<?> deleteResponse(@PathVariable(name = "userId") Long userId, @PathVariable(name="landLordId") Long landLordId) {
        return landLordService.deleteLandLord(userId, landLordId);
    }

    private LandLord convertToEntity(SaveLandLordResource resource) {
        return mapper.map(resource, LandLord.class);
    }

    private LandLordResource convertToResource(LandLord entity) {
        return mapper.map(entity, LandLordResource.class);
    }

    @PostConstruct
    public void init() {
        mapper.addMappings(new PropertyMap<LandLord, LandLordResource>() {
            @Override
            protected void configure() {
                map().setSubscriptionName(source.getSubscription().getName());
            }
        });
    }

}
