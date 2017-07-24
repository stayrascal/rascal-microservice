package com.stayrascal.cloud.address.functional.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.stayrascal.cloud.address.contract.command.UpdateAddressCommand;

import com.exmertec.dummie.Dummie;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Test;

@DatabaseSetup("classpath:AddressResourceFunctionalTest.xml")
@DatabaseTearDown("classpath:AddressResourceFunctionalTest.xml")
public class AddressResourceFunctionalTest extends BaseFunctionalTest {
    private static final String USER_ID = "497f60a8-93ad-4f47-a54d-e99d651fcb4e";

    @Test
    public void should_success_get_address_by_address_id() throws Exception {
        given()
                .header("Content-Type", "application/json")
                .when()
                .get("addresss/" + USER_ID)
                .then()
                .statusCode(is(200))
                .body("id", equalTo(USER_ID))
                .body("nickname", equalTo("name"))
                .body("email", equalTo("xxx@gg"))
                .body("mobile", equalTo("134567"));
    }

    @Test
    public void should_throw_404_when_get_address_with_invalid_address_id() throws Exception {
        String invalidAddressId = "invalidAddressId";
        given()
                .header("Content-Type", "application/json")
                .when()
                .get("addresss/" + invalidAddressId)
                .then()
                .statusCode(is(404));
    }

    @Test
    public void should_success_update_address_info() throws Exception {
        UpdateAddressCommand command = Dummie.create(UpdateAddressCommand.class);
        given()
                .header("Content-Type", "application/json")
                .body(command)
                .when()
                .put("addresss/" + USER_ID)
                .then()
                .statusCode(is(200));
    }
}
