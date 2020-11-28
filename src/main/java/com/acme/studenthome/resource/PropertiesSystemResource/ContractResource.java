package com.acme.studenthome.resource.PropertiesSystemResource;


import com.acme.studenthome.domain.model.PropertiesSystem.EContractStatus;
import lombok.Data;

import java.util.Date;


@Data
public class ContractResource {
    private Long id;
    private String description;
    private Float amount;
    private EContractStatus state;
    private String firstNameStudent;
    private String lastNameStudent;
    private Date createdAt;
    private Long propertyId;
    private Long studentId;
}
