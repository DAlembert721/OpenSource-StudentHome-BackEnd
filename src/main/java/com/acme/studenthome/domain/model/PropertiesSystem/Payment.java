package com.acme.studenthome.domain.model.PropertiesSystem;

import com.acme.studenthome.domain.model.AuditModel;
import com.acme.studenthome.domain.model.LocationsSystem.District;
import com.acme.studenthome.domain.model.UserAccountSystem.StudentSystem.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "payments")
@Data
public class Payment extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 250)
    private String image;

    @NotNull
    private Float pay;


    @NotNull
    private Boolean checked;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contract_id")
    @JsonIgnore
    private Contract contract;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

}
