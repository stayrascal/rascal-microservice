package com.stayrascal.cloud.organization.contract.dto;

import com.stayrascal.cloud.organization.contract.enumeration.OrganizationType;

import java.util.Date;

public class OrganizationDto {
    private String id;

    private String name;

    private OrganizationDto superior;

    private OrganizationType type;

    private Date timeCreated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrganizationDto getSuperior() {
        return superior;
    }

    public void setSuperior(OrganizationDto superior) {
        this.superior = superior;
    }

    public OrganizationType getType() {
        return type;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }
}
