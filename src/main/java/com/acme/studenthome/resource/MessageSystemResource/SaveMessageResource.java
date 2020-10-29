package com.acme.studenthome.resource.MessageSystemResource;

import lombok.Data;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SaveMessageResource {

    @NotNull
    @Lob
    @Size(max = 1000)
    private String text;
}
