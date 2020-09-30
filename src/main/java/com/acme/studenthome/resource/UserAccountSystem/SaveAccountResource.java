package com.acme.studenthome.resource.UserAccountSystem;

import lombok.Data;

import javax.persistence.Column;
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
    private Long phone;

}
