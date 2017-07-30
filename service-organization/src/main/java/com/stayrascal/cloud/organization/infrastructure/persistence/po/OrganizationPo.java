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
    @Column(name = "status", nullable = false, length = 32)
    private OrganizationType status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrganizationType getStatus() {
        return status;
    }

    public void setStatus(OrganizationType status) {
        this.status = status;
    }

    public String getSuperiorId() {
        return superiorId;
    }

    public void setSuperiorId(String superiorId) {
        this.superiorId = superiorId;
    }
}
