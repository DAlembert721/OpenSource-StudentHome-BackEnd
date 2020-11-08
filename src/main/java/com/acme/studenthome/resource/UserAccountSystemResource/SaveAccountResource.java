package com.acme.studenthome.resource.UserAccountSystemResource;

import lombok.Data;
import org.apache.tomcat.jni.Address;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SaveAccountResource {

    @NotNull
    @Size(max = 30)
    private String firstName;

    @NotNull
    @Size(max = 30)
    private String lastName;

    @NotNull
    @Size(max = 15)
    private String dni;

    @NotNull
    @Size(max = 20)
    private String phone;

    @Size(max = 250)
    private String description;

    @Size(max = 250)
    private String image;

}
