package com.acme.studenthome.resource.UserAccountSystemResource.StudentSystemResource;

import lombok.Data;

@Data
public class EducationCenterResource {
    private Long id;

    private Long id;

    private String name;

    private String address;

    private String districtName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}
