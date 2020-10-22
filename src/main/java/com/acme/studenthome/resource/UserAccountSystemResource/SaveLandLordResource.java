package com.acme.studenthome.resource.UserAccountSystemResource;

import com.acme.studenthome.resource.UserAccountSystemResource.SaveAccountResource;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class SaveLandLordResource extends SaveAccountResource {

    @NotNull
    private Long subscriptionId;
}
