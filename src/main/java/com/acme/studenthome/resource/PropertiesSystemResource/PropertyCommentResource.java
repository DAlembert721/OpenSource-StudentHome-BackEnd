package com.acme.studenthome.resource.PropertiesSystemResource;

import lombok.Data;

import java.util.Date;

@Data
public class PropertyCommentResource {

    private Long id;
    private Long score;
    private String comment;
    private Date createdAt;
    private String studentFirstName;
    private String studentLastName;
}
