package com.acme.studenthome.resource.PropertiesSystemResource;

import lombok.Data;

import java.util.Date;


@Data
public class PaymentResource {

    private Long id;

    private Float pay;

    private String image;

    private Boolean checked;

    private Date createdAt;

    private Date updateAt;
}
