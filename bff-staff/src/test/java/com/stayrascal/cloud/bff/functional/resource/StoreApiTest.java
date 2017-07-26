package com.stayrascal.cloud.bff.functional.resource;

import com.stayrascal.cloud.bff.functional.resource.mock.MockStoreClient;

import org.springframework.beans.factory.annotation.Autowired;

public class StoreApiTest extends BaseFunctionalTest {

    @Autowired
    private MockStoreClient mockStoreClient;
}
