package com.acme.studenthome.resource.UserAccountSystem.StudentSystemResource;

import com.acme.studenthome.resource.UserAccountSystem.AccountResource;
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
