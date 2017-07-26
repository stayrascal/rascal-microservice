package com.stayrascal.cloud.bff.functional.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import com.stayrascal.cloud.bff.functional.resource.mock.MockProductServiceClient;
import com.stayrascal.cloud.bff.functional.resource.mock.MockStoreProductServiceClient;
import com.stayrascal.cloud.common.contract.enumeration.SortType;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductApiTest extends BaseFunctionalTest {

    @Autowired
    private MockProductServiceClient productServiceClient;

    @Autowired
    private MockStoreProductServiceClient storeProductServiceClient;

    @Test
    public void shouldSuccessReturnWhenListProduct() throws Exception {
        given()
                .header("Content-Type", "application/json")
                .param("page_size", 10)
                .param("page_index", 1)
                .param("sort_type", SortType.ASC)
                .param("sort_by", "timeCreated")
                .when()
                .get("products/")
                .then()
                .statusCode(is(200));
    }
}
