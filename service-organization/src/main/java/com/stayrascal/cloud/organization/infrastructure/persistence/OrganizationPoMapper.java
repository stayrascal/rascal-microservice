package com.stayrascal.cloud.organization.infrastructure.persistence;

import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.organization.domain.entity.Organization;

public class OrganizationPoMapper extends DefaultMapper {
    public OrganizationPoMapper() {
        register(OrganizationPo.class, Organization.class);
    }
}
