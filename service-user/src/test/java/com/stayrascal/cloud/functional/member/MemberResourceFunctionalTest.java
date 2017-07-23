package com.stayrascal.cloud.functional.member;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.stayrascal.cloud.functional.BaseFunctionalTest;
import com.stayrascal.clould.common.contract.enumeration.SortType;

import com.exmertec.dummie.Dummie;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import com.stayrscal.cloud.user.member.contract.Gender;
import com.stayrscal.cloud.user.member.contract.command.CreateMemberCommand;
import com.stayrscal.cloud.user.member.contract.command.UpdateMemberCommand;
import org.junit.Test;

@DatabaseSetups({
        @DatabaseSetup(value = "classpath:member/MemberResourceFunctionalTest.xml",
                type = DatabaseOperation.TRUNCATE_TABLE),
        @DatabaseSetup(value = "classpath:member/MemberResourceFunctionalTest.xml")
})
public class MemberResourceFunctionalTest extends BaseFunctionalTest {
    private static final String MEMBER_ID = "497f60a8-93ad-4f47-a54d-e99d651fcb4e";

    @Test
    public void shouldSuccessGetMemberById() throws Exception {
        given()
                .header("Content-Type", "application/json")
                .when()
                .get("members/" + MEMBER_ID)
                .then()
                .statusCode(is(200))
                .body("id", equalTo(MEMBER_ID))
                .body("nickname", equalTo("name"))
                .body("email", equalTo("xxx@gg"))
                .body("mobile", equalTo("134567"))
                .body("gender", equalTo(Gender.MALE.name()));
    }

    @Test
    public void shouldThrow404WhenGetMemberWithInvalidId() throws Exception {
        String invalidUserId = "invalidUserId";
        given()
                .header("Content-Type", "application/json")
                .when()
                .get("members/" + invalidUserId)
                .then()
                .statusCode(is(404));
    }

    @Test
    public void shouldSuccessUpdateMemberInfo() throws Exception {
        UpdateMemberCommand command = Dummie.create(UpdateMemberCommand.class);
        given()
                .header("Content-Type", "application/json")
                .body(command)
                .when()
                .put("members/" + MEMBER_ID)
                .then()
                .statusCode(is(204));
    }

    @Test
    public void shouldSuccessGetMemberList() throws Exception {
        given()
                .header("Content-Type", "application/json")
                .param("page_size", "20")
                .param("page_index", "0")
                .param("sort_type", SortType.ASC)
                .param("sort_by", "timeCreated")
                .when()
                .get("members")
                .then()
                .statusCode(is(200))
                .body("totalCount", equalTo(1));
    }

    @Test
    public void shouldSuccessCreateMember() throws Exception {
        CreateMemberCommand command = new CreateMemberCommand();
        command.setNickname("nickname");

        given()
                .header("Content-Type", "application/json")
                .body(command)
                .when()
                .post("members")
                .then()
                .statusCode(is(201));
    }
}
