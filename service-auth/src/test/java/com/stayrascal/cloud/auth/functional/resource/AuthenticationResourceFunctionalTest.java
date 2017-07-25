package com.stayrascal.cloud.auth.functional.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Test;

@DatabaseSetup("classpath:AuthenticationResourceFunctionalTest.xml")
@DatabaseTearDown("classpath:AuthenticationResourceFunctionalTest.xml")
public class AuthenticationResourceFunctionalTest extends BaseFunctionalTest {
    private static final String USER_ID = "497f60a8-93ad-4f47-a54d-e99d651fcb4e";

    @Test
    public void should_success_get_auth_by_auth_id() throws Exception {
        given()
                .header("Content-Type", "application/json")
                .when()
                .get("auths/" + USER_ID)
                .then()
                .statusCode(is(200))
                .body("id", equalTo(USER_ID))
                .body("nickname", equalTo("name"))
                .body("email", equalTo("xxx@gg"))
                .body("mobile", equalTo("134567"));
    }

    @Test
    public void should_throw_404_when_get_auth_with_invalid_auth_id() throws Exception {
        String invalidAuthId = "invalidAuthId";
        given()
                .header("Content-Type", "application/json")
                .when()
                .get("auths/" + invalidAuthId)
                .then()
                .statusCode(is(404));
    }

}
