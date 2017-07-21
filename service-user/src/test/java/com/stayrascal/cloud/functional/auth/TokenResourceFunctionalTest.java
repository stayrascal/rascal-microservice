package com.stayrascal.cloud.functional.auth;


import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.stayrascal.cloud.common.constant.DefaultValues;
import com.stayrascal.cloud.common.util.JwtTokenCodec;
import com.stayrascal.cloud.functional.BaseFunctionalTest;
import com.stayrascal.clould.common.contract.auth.Identity;
import com.stayrascal.clould.common.contract.auth.IdentityType;
import com.stayrscal.cloud.user.auth.contract.command.CreateTokenCommand;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

@DatabaseSetup(value = "classpath:auth/tokenResourceTest.xml")
public class TokenResourceFunctionalTest extends BaseFunctionalTest {
    private JwtTokenCodec jwtTokenCodec = new JwtTokenCodec(DefaultValues.Auth.tokenSecret(),
            DefaultValues.Auth.tokenExpiredMinute());

    @Test
    public void shouldSuccessCreateTokenByPasswordForMember() throws Exception {
        CreateTokenCommand command = new CreateTokenCommand("password");
        String authId = "1";

        given()
                .header("Content-Type", "application/json")
                .body(command)
                .when()
                .post("authentications/" + authId + "/tokens")
                .then()
                .statusCode(is(201))
                .body("id", not(isEmptyOrNullString()));
    }

    @Test
    public void shouldSuccessCreateTokenByPasswordForStaff() throws Exception {
        CreateTokenCommand command = new CreateTokenCommand("password");

        String authId = "4";

        given()
                .header("Content-Type", "application/json")
                .body(command)
                .when()
                .post("authentications/" + authId + "/tokens")
                .then()
                .statusCode(is(201))
                .body("id", not(isEmptyOrNullString()));
    }

    @Test
    public void shouldSuccessGetTokenByEmailKeychain() throws Exception {
        CreateTokenCommand command = new CreateTokenCommand("password");
        String authId = "2";

        given()
                .header("Content-Type", "application/json")
                .body(command)
                .when()
                .post("authentications/" + authId + "/tokens")
                .then()
                .statusCode(is(201))
                .body("id", not(isEmptyOrNullString()));
    }

    @Test
    public void shouldForbidWhenUseWrongPassword() throws Exception {
        CreateTokenCommand command = new CreateTokenCommand("wrong_password");
        String authId = "1";

        given()
                .header("Content-Type", "application/json")
                .body(command)
                .when()
                .post("authentications/" + authId + "/tokens")
                .then()
                .statusCode(is(400));
    }

    @Test
    public void shouldForbidWhenGetIdentityWithInvalidToken() throws Exception {
        String invalidToken = "invalidToken";
        String authId = "1";
        given()
                .header("Content-Type", "application/json")
                .when()
                .get("authentications/" + authId + "/tokens/" + invalidToken)
                .then()
                .statusCode(is(403));
    }

    @Test
    public void shouldForbidWhenGetIdentityWithExpiredToken() throws Exception {
        String expiredToken =
                "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpemF0aW9uU2l6ZSI6MCwiaWRlbnRpdHlUeXBlIjoiTUVNQkVSIiwiaWR"
                        + "lbnRpdHlJZCI6Im1lbWJlciIsImV4cCI6MTQ5MzQ3ODE5MH0.FcAWkuPuaOrlywXrTvuLhSaDPdy52QB9uGJBXfeZ0Eo";

        String authId = "2";

        given()
                .header("Content-Type", "application/json")
                .when()
                .get("authentications/" + authId + "/tokens/" + expiredToken)
                .then()
                .statusCode(is(403));

    }

    @Test
    public void shouldSuccessGetIdentityByToken() throws Exception {
        Identity identity = new Identity(IdentityType.MEMBER, "member");

        String authId = "3";

        given()
                .header("Content-Type", "application/json")
                .when()
                .get("authentications/" + authId + "/tokens/"
                        + jwtTokenCodec.encodeToken(identity))
                .then()
                .statusCode(is(200))
                .body("identityType", equalTo(identity.getIdentityType().name()))
                .body("identityId", equalTo(identity.getIdentityId()));
    }
}

