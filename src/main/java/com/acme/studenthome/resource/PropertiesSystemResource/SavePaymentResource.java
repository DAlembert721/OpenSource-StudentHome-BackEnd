package com.acme.studenthome.resource.PropertiesSystemResource;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Data
public class SavePaymentResource {


    @NotNull
    private Float pay;

    @NotNull
    @Size(max = 250)
    private String image;

    @Size(max = 250)
    private String comment;

}
