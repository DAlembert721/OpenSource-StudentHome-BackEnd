package com.acme.studenthome.domain.model.UserAccountSystem.StudentSystem;

import com.acme.studenthome.domain.model.AuditModel;
import com.acme.studenthome.domain.model.UserAccountSystem.LandLord;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "student_opinions")
@Data
public class StudentOpinion extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long score;

    @NotNull
    @Size(max = 250)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "land_lord_id", nullable = false)
    @JsonIgnore
    private LandLord landLord;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    @JsonIgnore
    private Student student;


}
