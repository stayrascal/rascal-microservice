package com.stayrascal.cloud.organization.domain.factory;

import com.stayrascal.cloud.common.jpa.UniqueKeyGenerator;
import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.organization.contract.command.CreateOrganizationCommand;
import com.stayrascal.cloud.organization.contract.dto.OrganizationDto;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrganizationFactory {
    private final UniqueKeyGenerator uniqueKeyGenerator;
    private final DefaultMapper mapper;

    @Autowired
    public OrganizationFactory(UniqueKeyGenerator uniqueKeyGenerator) {
        this.uniqueKeyGenerator = uniqueKeyGenerator;
        this.mapper = DefaultMapper.builder().register(CreateOrganizationCommand.class, OrganizationDto.class).build();
    }

    public OrganizationDto createOrganization(CreateOrganizationCommand command) {
        OrganizationDto organization = mapper.map(command, OrganizationDto.class);
        organization.setId(uniqueKeyGenerator.generateKey());
        organization.setTimeCreated(DateTime.now().toDate());
        return organization;
    }
}
