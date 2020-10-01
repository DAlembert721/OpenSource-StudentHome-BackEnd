package com.acme.studenthome.resource.UserAccountSystem;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class SaveLandLordResource extends SaveAccountResource{

    @NotNull
    private Long subscriptionId;
}
