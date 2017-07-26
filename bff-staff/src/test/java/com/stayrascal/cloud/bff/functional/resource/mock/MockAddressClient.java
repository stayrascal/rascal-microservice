package com.stayrascal.cloud.bff.functional.resource.mock;

import com.stayrascal.cloud.address.contract.client.AddressServiceClient;
import com.stayrascal.cloud.address.contract.dto.AddressDto;

import org.springframework.stereotype.Component;

@Component
public class MockAddressClient implements AddressServiceClient {
    @Override
    public AddressDto get(Long id) {
        return null;
    }
}
