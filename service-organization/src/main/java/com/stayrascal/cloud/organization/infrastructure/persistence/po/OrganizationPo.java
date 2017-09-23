package com.stayrascal.cloud.organization.infrastructure.persistence.po;

import com.stayrascal.cloud.common.jpa.BasePo;
import com.stayrascal.cloud.organization.contract.enumeration.OrganizationType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "ORGANIZATION")
public class OrganizationPo extends BasePo {

    @Column(name = "name", nullable = false, updatable = false)
    private String name;

    @Column(name = "superior_id", nullable = false, updatable = false)
    private String superiorId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 32)
    private OrganizationType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrganizationType getType() {
        return type;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }

    public String getSuperiorId() {
        return superiorId;
    }

    public void setSuperiorId(String superiorId) {
        this.superiorId = superiorId;
    }
}
