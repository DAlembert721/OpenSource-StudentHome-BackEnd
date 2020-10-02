package com.acme.studenthome.resource.PropertiesSystemResource;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SavePropertyResource {
    @NotNull
    private Long rooms;

    @NotNull
    private Float size;

    @NotNull
    private Float cost;

    @NotNull
    private Boolean active;

    @NotNull
    @Size(max = 100)
    private String address;

    private Long districtId;
}
