package com.acme.studenthome.resource.PropertiesSystemResource;

import com.acme.studenthome.domain.model.PropertiesSystem.ERequestStatus;
import lombok.Data;

import java.util.Date;


@Data
public class RequestResource {
    private Long id;
    private String content;
    private ERequestStatus state;
    private String firstNameStudent;
    private String lastNameStudent;
    private Date createdAt;
    private String firstNameLandlord;
    private String lastNameLandlord;
}
