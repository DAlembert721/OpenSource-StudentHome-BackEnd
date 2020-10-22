package com.acme.studenthome.resource.UserAccountSystemResource.StudentSystemResource;


import com.acme.studenthome.resource.UserAccountSystemResource.AccountResource;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class StudentResource extends AccountResource {

    private String address;

    private String image;

    private String districtName;

    private String educationCenterName;
}
