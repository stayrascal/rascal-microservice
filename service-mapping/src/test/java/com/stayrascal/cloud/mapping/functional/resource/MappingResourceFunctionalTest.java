package com.stayrascal.cloud.mapping.functional.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import com.stayrascal.cloud.mapping.contract.command.CreateStaffAddressMappingCommand;

import com.exmertec.dummie.Dummie;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Test;

@DatabaseSetup("classpath:AddressMappingResourceFunctionalTest.xml")
@DatabaseTearDown("classpath:AddressMappingResourceFunctionalTest.xml")
public class MappingResourceFunctionalTest extends BaseFunctionalTest {
    private static final Long ADDRESS_ID = 2L;

    @Test
    public void shouldThrow404WhenGetAddressIdWithInvalidAddUserId() throws Exception {
        String invalidUserId = "invalidUserId";
        given()
                .header("Content-Type", "application/json")
                .when()
                .get("mappings/" + invalidUserId)
                .then()
                .statusCode(is(404));
    }

    @Test
    public void shouldSuccessAddAddressMapping() throws Exception {
        CreateStaffAddressMappingCommand command = Dummie.create(CreateStaffAddressMappingCommand.class);
        given()
                .header("Content-Type", "application/json")
                .body(command)
                .when()
                .post("mappings/add/address")
                .then()
                .statusCode(is(201));
    }
}
