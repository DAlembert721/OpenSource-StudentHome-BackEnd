package com.acme.studenthome.resource.PropertiesSystemResource;


import lombok.Data;

@Data
public class ContractResource {
    private Long id;
    private String description;
    private Float amount;
    private Boolean state;
}
