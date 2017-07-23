package com.stayrascal.cloud.functional.auth;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.stayrascal.cloud.functional.BaseFunctionalTest;
import com.stayrascal.clould.common.contract.enumeration.SortType;

import com.exmertec.dummie.Dummie;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import com.stayrscal.cloud.user.auth.contract.command.CreateAuthenticationCommand;
import org.junit.Test;

@DatabaseSetups({
        @DatabaseSetup(value = "classpath:auth/authenticationResourceTest.xml", type = DatabaseOperation.TRUNCATE_TABLE),
        @DatabaseSetup(value = "classpath:auth/authenticationResourceTest.xml")
})
public class AuthenticationResourceFunctionalTest extends BaseFunctionalTest {

    @Test
    public void shouldSuccessCreateAuthentication() throws Exception {
        CreateAuthenticationCommand command = Dummie.create(CreateAuthenticationCommand.class);

        given().header("Content-Type", "application/json")
                .body(command)
                .when()
                .post("authentications")
                .then()
                .statusCode(is(201));
    }

    @Test
    public void shouldSuccessGetAuthentication() throws Exception {
        String authId = "id";

        given().header("Content-Type", "application/json")
                .when()
                .get("authentications/" + authId)
                .then()
                .statusCode(is(200))
                .body("authenticationName", equalTo("authenticationName"));
    }

    @Test
    public void shouldSuccessListAuthentication() throws Exception {
        given().header("Content-Type", "application/json")
                .param("account_id", "userId")
                .param("account_role", "MEMBER")
                .param("account_group", "MEMBER")
                .param("page_size", 10)
                .param("page_index", 0)
                .param("sort_type", SortType.ASC)
                .param("sort_by", "timeCreated")
                .when()
                .get("authentications")
                .then()
                .statusCode(is(200))
                .body("totalCount", equalTo(1))
                .body("items[0].id", equalTo("id"));
    }
}