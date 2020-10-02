package com.acme.studenthome.resource.UserAccountSystemResource.StudentSystemResource;


import com.acme.studenthome.resource.UserAccountSystemResource.AccountResource;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@Data
public class SaveStudentResource extends AccountResource {

    @NotNull
    @Size(max = 100)
    private String address;

    @NotNull
    @Size(max = 100)
    private String image;

    @NotNull
    private Long districtId;

    @NotNull
    private Long educationCenterId;
}
