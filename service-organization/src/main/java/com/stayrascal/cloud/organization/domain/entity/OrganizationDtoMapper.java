package com.stayrascal.cloud.organization.domain.entity;

import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.organization.contract.dto.OrganizationDto;

class OrganizationDtoMapper extends DefaultMapper {
    public OrganizationDtoMapper() {
        register(OrganizationDto.class, Organization.class);
    }
}
