package com.stayrascal.cloud.organization.functional.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.stayrascal.cloud.organization.contract.command.UpdateOrganizationCommand;

import com.exmertec.dummie.Dummie;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Test;

@DatabaseSetup("classpath:OrganizationResourceFunctionalTest.xml")
@DatabaseTearDown("classpath:OrganizationResourceFunctionalTest.xml")
public class OrganizationResourceFunctionalTest extends BaseFunctionalTest {
    private static final String ORGANIZATION_ID = "497f60a8-93ad-4f47-a54d-e99d651fcb4e";

    @Test
    public void shouldSuccessGetOrganizationByOrganizationId() throws Exception {
        given()
                .header("Content-Type", "application/json")
                .when()
                .get("organizations/" + ORGANIZATION_ID)
                .then()
                .statusCode(is(200))
                .body("id", equalTo(ORGANIZATION_ID))
                .body("name", equalTo("name"))
                .body("superiorId", equalTo("497f60a8-93ad-4f47-a54d-e99d651fcb4f"));
    }

    @Test
    public void shouldThrow404WhenGetOrganizationWithInvalidOrganizationId() throws Exception {
        String invalidOrganizationId = "invalidOrganizationId";
        given()
                .header("Content-Type", "application/json")
                .when()
                .get("organizations/" + invalidOrganizationId)
                .then()
                .statusCode(is(404));
    }

    @Test
    public void shouldSuccessUpdateOrganizationInfo() throws Exception {
        UpdateOrganizationCommand command = Dummie.create(UpdateOrganizationCommand.class);
        given()
                .header("Content-Type", "application/json")
                .body(command)
                .when()
                .put("organizations/" + ORGANIZATION_ID)
                .then()
                .statusCode(is(200));
    }
}
