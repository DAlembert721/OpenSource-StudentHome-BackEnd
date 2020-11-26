package com.acme.studenthome.domain.model.PropertiesSystem;

import com.acme.studenthome.domain.model.AuditModel;
import com.acme.studenthome.domain.model.LocationsSystem.District;
import com.acme.studenthome.domain.model.UserAccountSystem.LandLord;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "properties")
@Data
public class Property extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long rooms;

    @NotNull
    private Float size;

    @NotNull
    private Float cost;

    @NotNull
    private Boolean active;

    @NotNull
    @Size(max = 100)
    private String address;

    @Size(max = 100)
    private String title;

    @Size(max = 400)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "property_services",
            joinColumns = { @JoinColumn(name = "property_id")},
            inverseJoinColumns = { @JoinColumn(name = "service_id")})
    private List<Service> services;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "district_id", nullable = false)
    @JsonIgnore
    private District district;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "land_lord_id", nullable = false)
    @JsonIgnore
    private LandLord landLord;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<PropertyImage> propertyImages;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRooms() {
        return rooms;
    }

    public void setRooms(Long rooms) {
        this.rooms = rooms;
    }

    public Float getSize() {
        return size;
    }

    public void setSize(Float size) {
        this.size = size;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public LandLord getLandLord() {
        return landLord;
    }

    public void setLandLord(LandLord landLord) {
        this.landLord = landLord;
    }

    public boolean haveService(Service service) {
        return this.getServices().contains(service);
    }

    public Property addService(Service service) {
        if(!this.haveService(service))
            this.getServices().add(service);
        return this;
    }

    public Property removeService(Service service) {
        if(this.haveService(service))
            this.getServices().remove(service);
        return this;
    }

}


