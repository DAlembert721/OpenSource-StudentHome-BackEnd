package com.acme.studenthome.resource.PropertiesSystemResource;


import com.acme.studenthome.domain.model.AuditModel;
import com.acme.studenthome.domain.model.PropertiesSystem.ContractStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


@Data
public class ContractResource {
    private Long id;
    private String description;
    private Float amount;
    private ContractStatus state;
    private String firstNameStudent;
    private String lastNameStudent;
    private Date createdAt;
    private Long propertyId;
    private Long studentId;
}
