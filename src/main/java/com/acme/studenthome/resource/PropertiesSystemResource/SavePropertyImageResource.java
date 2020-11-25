package com.acme.studenthome.resource.PropertiesSystemResource;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SavePropertyImageResource {
    @NotNull
    private String url;
}
