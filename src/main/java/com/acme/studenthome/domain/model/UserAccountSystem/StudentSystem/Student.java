package com.acme.studenthome.domain.model.UserAccountSystem.StudentSystem;

import com.acme.studenthome.domain.model.LocationsSystem.District;
import com.acme.studenthome.domain.model.UserAccountSystem.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@Entity
@PrimaryKeyJoinColumn(name = "account_id")
@Table(name = "students")
@Data
public class Student extends Account {

    @NotNull
    @Size(max = 100)
    private String address;

    @NotNull
    @Size(max = 100)
    private String image;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "education_center_id", nullable = false)
    @JsonIgnore
    private EducationCenter educationCenter;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "district_id", nullable = false)
    @JsonIgnore
    private District district;

}
