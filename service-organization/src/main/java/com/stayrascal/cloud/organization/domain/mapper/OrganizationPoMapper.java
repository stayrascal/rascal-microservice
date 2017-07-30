package com.stayrascal.cloud.organization.domain.mapper;

import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.organization.domain.entity.Organization;
import com.stayrascal.cloud.organization.infrastructure.persistence.po.OrganizationPo;

import org.springframework.stereotype.Component;

@Component
public class OrganizationPoMapper extends DefaultMapper {
    public OrganizationPoMapper() {
        register(OrganizationPo.class, Organization.class);
    }

    public Organization organizationFromPo(OrganizationPo organizationPo) {
        return map(organizationPo, Organization.class);
    }

    public OrganizationPo organizationToPo(Organization organization) {
        return map(organization, OrganizationPo.class);
    }
}
