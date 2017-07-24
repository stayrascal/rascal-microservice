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
    private static final String USER_ID = "497f60a8-93ad-4f47-a54d-e99d651fcb4e";

    @Test
    public void should_success_get_organization_by_organization_id() throws Exception {
        given()
            .header("Content-Type", "application/json")
            .when()
            .get("organizations/" + USER_ID)
            .then()
            .statusCode(is(200))
            .body("id", equalTo(USER_ID))
            .body("nickname", equalTo("name"))
            .body("email", equalTo("xxx@gg"))
            .body("mobile", equalTo("134567"));
    }

    @Test
    public void should_throw_404_when_get_organization_with_invalid_organization_id() throws Exception {
        String invalidOrganizationId = "invalidOrganizationId";
        given()
            .header("Content-Type", "application/json")
            .when()
            .get("organizations/" + invalidOrganizationId)
            .then()
            .statusCode(is(404));
    }

    @Test
    public void should_success_update_organization_info() throws Exception {
        UpdateOrganizationCommand command = Dummie.create(UpdateOrganizationCommand.class);
        given()
            .header("Content-Type", "application/json")
            .body(command)
            .when()
            .put("organizations/" + USER_ID)
            .then()
            .statusCode(is(200));
    }
}
