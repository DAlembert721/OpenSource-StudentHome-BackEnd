package com.acme.studenthome.resource.PropertiesSystemResource;

import lombok.Data;


@Data
public class PaymentResource {

    private Long id;

    private Float amount;

    private String image;

    private Boolean checked;
}
