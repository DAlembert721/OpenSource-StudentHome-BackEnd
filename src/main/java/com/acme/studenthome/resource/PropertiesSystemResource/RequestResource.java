package com.acme.studenthome.resource.PropertiesSystemResource;

import lombok.Data;


@Data
public class RequestResource {
    private Long id;
    private String content;
    private Boolean state;
}
