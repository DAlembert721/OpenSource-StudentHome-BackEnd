package com.acme.studenthome.resource.UserAccountSystemResource;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LandLordResource extends AccountResource{
    private String subscriptionName;
}
