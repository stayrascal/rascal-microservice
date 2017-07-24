package com.stayrascal.cloud.store.functional.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.stayrascal.cloud.store.contract.command.UpdateStoreCommand;

import com.exmertec.dummie.Dummie;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Test;

@DatabaseSetup("classpath:StoreResourceFunctionalTest.xml")
@DatabaseTearDown("classpath:StoreResourceFunctionalTest.xml")
public class StoreResourceFunctionalTest extends BaseFunctionalTest {
    private static final String USER_ID = "497f60a8-93ad-4f47-a54d-e99d651fcb4e";

    @Test
    public void should_success_get_store_by_store_id() throws Exception {
        given()
                .header("Content-Type", "application/json")
                .when()
                .get("stores/" + USER_ID)
                .then()
                .statusCode(is(200))
                .body("id", equalTo(USER_ID))
                .body("nickname", equalTo("name"))
                .body("email", equalTo("xxx@gg"))
                .body("mobile", equalTo("134567"));
    }

    @Test
    public void should_throw_404_when_get_store_with_invalid_store_id() throws Exception {
        String invalidStoreId = "invalidStoreId";
        given()
                .header("Content-Type", "application/json")
                .when()
                .get("stores/" + invalidStoreId)
                .then()
                .statusCode(is(404));
    }

    @Test
    public void should_success_update_store_info() throws Exception {
        UpdateStoreCommand command = Dummie.create(UpdateStoreCommand.class);
        given()
                .header("Content-Type", "application/json")
                .body(command)
                .when()
                .put("stores/" + USER_ID)
                .then()
                .statusCode(is(200));
    }
}
