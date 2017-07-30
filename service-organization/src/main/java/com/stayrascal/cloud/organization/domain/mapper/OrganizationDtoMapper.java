package com.stayrascal.cloud.organization.domain.mapper;

import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.organization.contract.dto.OrganizationDto;
import com.stayrascal.cloud.organization.domain.entity.Organization;

import org.springframework.stereotype.Component;

@Component
public class OrganizationDtoMapper extends DefaultMapper {
    public OrganizationDtoMapper() {
        register(OrganizationDto.class, Organization.class);
    }

    public OrganizationDto organizationToDto(Organization organization) {
        return map(organization, OrganizationDto.class);
    }

    public Organization organizationFromDto(OrganizationDto organization) {
        return map(organization, Organization.class);
    }
}
