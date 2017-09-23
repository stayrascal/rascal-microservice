package com.stayrascal.cloud.bff.functional.resource.mock;

import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.mapping.contract.client.MappingServiceClient;
import com.stayrascal.cloud.mapping.contract.command.CreateStaffAddressMappingCommand;
import com.stayrascal.cloud.mapping.contract.command.CreateStaffOrderMappingCommand;

import org.springframework.stereotype.Component;

@Component
public class MockMappingClient implements MappingServiceClient {

    @Override
    public CreatedResult addAddressMapping(CreateStaffAddressMappingCommand command) {
        return null;
    }

    @Override
    public CreatedResult addOrderMapping(CreateStaffOrderMappingCommand command) {
        return null;
    }

    @Override
    public PageResult retrieveAddressIds(String userId) {
        return null;
    }

    @Override
    public PageResult retrieveOrderIds(String userId) {
        return null;
    }
}
