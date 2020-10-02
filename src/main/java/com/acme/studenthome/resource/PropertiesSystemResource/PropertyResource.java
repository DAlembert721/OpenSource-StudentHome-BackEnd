package com.acme.studenthome.resource.PropertiesSystemResource;

import com.acme.studenthome.domain.model.LocationsSystem.District;
import com.acme.studenthome.domain.model.PropertiesSystem.Service;
import com.acme.studenthome.domain.model.UserAccountSystem.LandLord;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class PropertyResource {

    private Long id;

    private Long rooms;

    private Float size;

    private Float cost;

    private Boolean active;

    private String address;

    private Long landLordId;

    private String landLordFirstName;

    private String landLordLastName;

}
