package com.stayrascal.cloud.organization.infrastructure.factory;

import com.stayrascal.cloud.common.ddd.EventSender;
import com.stayrascal.cloud.common.jpa.UniqueKeyGenerator;
import com.stayrascal.cloud.organization.contract.dto.OrganizationDto;
import com.stayrascal.cloud.organization.domain.entity.Organization;
import com.stayrascal.cloud.organization.infrastructure.persistence.OrganizationPo;
import com.stayrascal.cloud.organization.infrastructure.persistence.OrganizationPoMapper;
import com.stayrascal.cloud.organization.infrastructure.persistence.OrganizationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrganizationFactory {
    private final UniqueKeyGenerator uniqueKeyGenerator;
    private final EventSender eventSender;
    private final OrganizationPoMapper mapper;
    private final OrganizationRepository organizationRepository;

    @Autowired
    public OrganizationFactory(UniqueKeyGenerator uniqueKeyGenerator,
                          OrganizationRepository organizationRepository,
                          EventSender eventSender) {
        this.uniqueKeyGenerator = uniqueKeyGenerator;
        this.organizationRepository = organizationRepository;
        this.eventSender = eventSender;
        this.mapper = new OrganizationPoMapper();
    }

    public Organization createWithDto(OrganizationDto organizationDto) {
        OrganizationPo organization = mapper.map(organizationDto, OrganizationPo.class);
        organization.setId(uniqueKeyGenerator.generateKey());
        organizationRepository.save(organization);

        return toOrganization(organization).get();
    }

    public Optional<Organization> createWithId(String id) {
        return toOrganization(organizationRepository.ofId(id));
    }

    private Optional<Organization> toOrganization(OrganizationPo organizationPo) {
        if (organizationPo == null) {
            return Optional.empty();
        }
        Organization organization = mapper.map(organizationPo, Organization.class);
        organization.setNotifyChange(entity -> organizationRepository.update(mapper.map(entity, OrganizationPo.class)));
        return Optional.of(organization);
    }
}
