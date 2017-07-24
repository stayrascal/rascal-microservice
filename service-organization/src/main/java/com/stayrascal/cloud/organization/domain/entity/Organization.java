package com.stayrascal.cloud.organization.domain.entity;

import com.stayrascal.cloud.common.contract.enumeration.CommonStatus;
import com.stayrascal.cloud.organization.contract.dto.OrganizationDto;

import java.util.function.Consumer;

public class Organization {
    private String id;

    private CommonStatus status;

    private final OrganizationDtoMapper mapper;

    private Consumer<Organization> notifyChange;

    public Organization() {
        mapper = new OrganizationDtoMapper();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CommonStatus getStatus() {
        return status;
    }

    public void setStatus(CommonStatus status) {
        this.status = status;
    }

    public void setNotifyChange(Consumer<Organization> notifyChange) {
        this.notifyChange = notifyChange;
    }

    public void updateOrganization(OrganizationDto organization) {
        notifyChange.accept(this);
    }

    public OrganizationDto toDto() {
        return mapper.map(this, OrganizationDto.class);
    }
}
