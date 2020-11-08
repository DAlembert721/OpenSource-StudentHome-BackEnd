package com.acme.studenthome.resource.UserAccountSystemResource.StudentSystemResource;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


@Data
public class StudentOpinionResource {

    private Long id;
    private Long score;
    private String content;
    private Date createdAt;
    private String landlordFirstName;
    private String landlordLastName;

}
