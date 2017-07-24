package com.stayrascal.cloud.organization.contract.command;

import com.stayrascal.cloud.organization.contract.enumeration.OrganizationType;

public class CreateOrganizationCommand {
    private String name;

    private String superiorId;

    private OrganizationType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuperiorId() {
        return superiorId;
    }

    public void setSuperiorId(String superiorId) {
        this.superiorId = superiorId;
    }

    public OrganizationType getType() {
        return type;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }
}
