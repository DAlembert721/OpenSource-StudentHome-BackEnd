package com.acme.studenthome.resource.PropertiesSystemResource;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SaveContractResource {

    @NotNull
    @Size(max = 500)
    private String description;

    @NotNull
    private Float amount;

}
