package com.stayrascal.cloud.address.functional.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import com.stayrascal.cloud.address.contract.command.CreateAddressCommand;
import com.stayrascal.cloud.address.contract.command.UpdateAddressCommand;

import com.exmertec.dummie.Dummie;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Test;

@DatabaseSetup("classpath:AddressResourceFunctionalTest.xml")
@DatabaseTearDown("classpath:AddressResourceFunctionalTest.xml")
public class AddressResourceFunctionalTest extends BaseFunctionalTest {
    private static final Long ADDRESS_ID = 2L;

    @Test
    public void shouldThrow404WhenGetAddressWithInvalidAddressId() throws Exception {
        Long invalidAddressId = 0L;
        given()
                .header("Content-Type", "application/json")
                .when()
                .get("addresses/" + invalidAddressId)
                .then()
                .statusCode(is(404));
    }

    @Test
    public void shouldSuccessUpdateAddressInfo() throws Exception {
        UpdateAddressCommand command = Dummie.create(UpdateAddressCommand.class);
        given()
                .header("Content-Type", "application/json")
                .body(command)
                .when()
                .put("addresses/" + ADDRESS_ID)
                .then()
                .statusCode(is(200));
    }

    @Test
    public void shouldSuccessAddAddressInfo() throws Exception {
        CreateAddressCommand command = Dummie.create(CreateAddressCommand.class);
        given()
                .header("Content-Type", "application/json")
                .body(command)
                .when()
                .post("addresses/")
                .then()
                .statusCode(is(201));
    }
}
