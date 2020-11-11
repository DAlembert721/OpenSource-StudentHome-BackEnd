package com.acme.studenthome.resource.PropertiesSystemResource;

import lombok.Data;

import java.util.Date;


@Data
public class RequestResource {
    private Long id;
    private String content;
    private Boolean state;
    private String firstNameStudent;
    private String lastNameStudent;
    private Date createdAt;
    private String firstNameLandlord;
    private String lastNameLandlord;
}
