package com.stayrascal.cloud.organization.facade;

import com.stayrascal.cloud.common.constant.ErrorCode;
import com.stayrascal.cloud.common.ddd.EventSender;
import com.stayrascal.cloud.common.jersey.exception.NotFoundException;
import com.stayrascal.cloud.organization.contract.command.CreateOrganizationCommand;
import com.stayrascal.cloud.organization.contract.command.UpdateOrganizationCommand;
import com.stayrascal.cloud.organization.contract.dto.OrganizationDto;
import com.stayrascal.cloud.organization.domain.entity.Organization;
import com.stayrascal.cloud.organization.infrastructure.factory.OrganizationFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class OrganizationFacade {
    private final OrganizationFactory organizationFactory;
    private final EventSender eventSender;

    @Autowired
    public OrganizationFacade(OrganizationFactory organizationFactory,
                      EventSender eventSender) {
        this.organizationFactory = organizationFactory;
        this.eventSender = eventSender;
    }

    public OrganizationDto getOrganizationById(String id) {
        return organizationFactory.createWithId(id).orElseThrow(() ->
                new NotFoundException(ErrorCode.INTERNAL_ERROR, "Organization of id {} not found", id)
        ).toDto();
    }

    public String createOrganization(CreateOrganizationCommand createOrganizationCommand) {
        return null;
    }

    public void updateOrganizationInfo(String organizationId, UpdateOrganizationCommand command) {
        Organization organization = organizationFactory.createWithId(organizationId).orElseThrow(() ->
                new NotFoundException(ErrorCode.INTERNAL_ERROR, "Organization of id {} not found", organizationId));
        organization.setId(command.getName());
    }
}
