package com.acme.studenthome.resource.UserAccountSystemResource;

import lombok.Data;


@Data
public class AccountResource {
    private Long Id;
    private String firstName;
    private String lastName;
    private String dni;
    private Long phone;
    private String email;
    private String description;
    private String type;
}
