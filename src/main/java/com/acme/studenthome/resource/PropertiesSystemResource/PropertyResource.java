package com.acme.studenthome.resource.PropertiesSystemResource;

import com.acme.studenthome.domain.model.LocationsSystem.District;
import com.acme.studenthome.domain.model.PropertiesSystem.Service;
import com.acme.studenthome.domain.model.UserAccountSystem.LandLord;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

@Data
public class PropertyResource {

    private Long id;

    private Long rooms;

    private Float size;

    private Float cost;

    private Boolean active;

    private String address;

    private String title;

    private String description;

    private Long landLordId;

    private String landLordFirstName;

    private String landLordLastName;

    private Long districtId;

    private Long provinceId;

    private Long regionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRooms() {
        return rooms;
    }

    public void setRooms(Long rooms) {
        this.rooms = rooms;
    }

    public Float getSize() {
        return size;
    }

    public void setSize(Float size) {
        this.size = size;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getLandLordId() {
        return landLordId;
    }

    public void setLandLordId(Long landLordId) {
        this.landLordId = landLordId;
    }

    public String getLandLordFirstName() {
        return landLordFirstName;
    }

    public void setLandLordFirstName(String landLordFirstName) {
        this.landLordFirstName = landLordFirstName;
    }

    public String getLandLordLastName() {
        return landLordLastName;
    }

    public void setLandLordLastName(String landLordLastName) {
        this.landLordLastName = landLordLastName;
    }
}
