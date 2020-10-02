package com.acme.studenthome.resource.UserAccountSystem;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LandLordResource extends AccountResource{
    private String subscriptionName;
}
