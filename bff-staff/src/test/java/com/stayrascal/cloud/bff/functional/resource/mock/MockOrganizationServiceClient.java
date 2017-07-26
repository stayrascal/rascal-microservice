package com.stayrascal.cloud.bff.functional.resource.mock;

import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.organization.contract.client.OrganizationServiceClient;
import com.stayrascal.cloud.organization.contract.command.CreateOrganizationCommand;
import com.stayrascal.cloud.organization.contract.command.UpdateOrganizationCommand;
import com.stayrascal.cloud.organization.contract.dto.OrganizationDto;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MockOrganizationServiceClient implements OrganizationServiceClient {
    @Override
    public OrganizationDto getOrganization(String organizationId) {
        return null;
    }

    @Override
    public CreatedResult createOrganization(CreateOrganizationCommand command) {
        return null;
    }

    @Override
    public void updateOrganization(String id, UpdateOrganizationCommand updateCommand) {

    }

    @Override
    public PageResult listOrganizations(SortType sortType, String sortBy, Integer pageSize, Integer pageIndex, Map<String, String> queryMap) {
        return null;
    }
}
