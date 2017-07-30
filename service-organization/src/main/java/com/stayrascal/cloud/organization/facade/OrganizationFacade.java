package com.stayrascal.cloud.organization.facade;

import com.stayrascal.cloud.common.constant.ErrorCode;
import com.stayrascal.cloud.common.contract.query.SortQuery;
import com.stayrascal.cloud.common.jersey.exception.NotFoundException;
import com.stayrascal.cloud.organization.contract.command.CreateOrganizationCommand;
import com.stayrascal.cloud.organization.contract.command.UpdateOrganizationCommand;
import com.stayrascal.cloud.organization.contract.dto.OrganizationDto;
import com.stayrascal.cloud.organization.domain.entity.Organization;
import com.stayrascal.cloud.organization.domain.factory.OrganizationFactory;
import com.stayrascal.cloud.organization.domain.mapper.OrganizationDtoMapper;
import com.stayrascal.cloud.organization.infrastructure.persistence.JpaOrganizationRepository;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Transactional
public class OrganizationFacade {
    private final OrganizationFactory organizationFactory;
    private final JpaOrganizationRepository organizationRepository;
    private final OrganizationDtoMapper mapper;


    @Autowired
    public OrganizationFacade(OrganizationFactory organizationFactory,
                              JpaOrganizationRepository organizationRepository,
                              OrganizationDtoMapper mapper) {
        this.organizationFactory = organizationFactory;
        this.organizationRepository = organizationRepository;
        this.mapper = mapper;
    }

    public OrganizationDto getOrganizationById(String id) {
        Organization organization = getOrganization(id);
        return mapper.organizationToDto(organization);

    }

    private Organization getOrganization(String id) {
        return organizationRepository.ofId(id).orElseThrow(() ->
                new NotFoundException(ErrorCode.INTERNAL_ERROR, "Organization of id {} not found", id)
        );
    }

    public String createOrganization(CreateOrganizationCommand command) {
        return organizationRepository.insert(mapper.organizationFromDto(organizationFactory.createOrganization(command)));
    }

    public OrganizationDto updateOrganization(String organizationId, UpdateOrganizationCommand command) {
        Organization organization = getOrganization(organizationId);
        if (!Strings.isNullOrEmpty(command.getName())) {
            organization.setName(command.getName());
        }
        return mapper.organizationToDto(organizationRepository.update(organization));
    }

    public List<OrganizationDto> listOrganizations(SortQuery sortQuery, Map<String, String> queryMap) {
        List<Organization> organizations = organizationRepository.listOrganizations(sortQuery, queryMap);
        return organizations.stream().map(mapper::organizationToDto).collect(Collectors.toList());
    }
}
