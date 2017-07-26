package com.stayrascal.cloud.bff.functional.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import com.stayrascal.cloud.bff.functional.resource.mock.MockStoreClient;
import com.stayrascal.cloud.organization.contract.command.CreateStoreCommand;

import com.exmertec.dummie.Dummie;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class StoreApiTest extends BaseFunctionalTest {
    @Autowired
    private MockStoreClient mockStoreClient;

    @Test
    public void shouldCreateStore() throws Exception {
        CreateStoreCommand createStoreCommand = Dummie.create(CreateStoreCommand.class);
        createStoreCommand.setName("test-store");

        given()
                .header("Content-Type", "application/json")
                .body(createStoreCommand)
                .when()
                .post("stores")
                .then()
                .statusCode(is(201));
    }
}
