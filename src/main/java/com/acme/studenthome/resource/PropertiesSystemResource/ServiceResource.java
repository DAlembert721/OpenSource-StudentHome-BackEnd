package com.acme.studenthome.resource.PropertiesSystemResource;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ServiceResource {
    private Long id;

    private String name;

    private String description;
}
