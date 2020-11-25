package com.acme.studenthome.resource.PropertiesSystemResource;


import com.acme.studenthome.domain.model.AuditModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


@Data
public class ContractResource {
    private Long id;
    private String description;
    private Float amount;
    private Boolean state;
    private String firstNameStudent;
    private String lastNameStudent;
    private Date createdAt;
    private Long propertyId;
    private Long studentId;
}
