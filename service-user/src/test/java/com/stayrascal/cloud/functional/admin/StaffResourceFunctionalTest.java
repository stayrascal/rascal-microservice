package com.stayrascal.cloud.functional.admin;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.stayrascal.cloud.functional.BaseFunctionalTest;
import com.stayrascal.clould.common.contract.enumeration.SortType;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import com.stayrscal.cloud.user.admin.contract.command.CreateStaffCommand;
import org.junit.Test;

import java.util.ArrayList;

@DatabaseSetups({
        @DatabaseSetup(value = "classpath:admin/StaffResourceFunctionalTest.xml", type = DatabaseOperation.TRUNCATE_TABLE),
        @DatabaseSetup(value = "classpath:admin/StaffResourceFunctionalTest.xml")
})
public class StaffResourceFunctionalTest extends BaseFunctionalTest {
    @Test
    public void shouldSuccessGetStaffById() throws Exception {
        String staffId = "test_admin_user_id";
        given()
                .header("Content-Type", "application/json")
                .when()
                .get("staffs/" + staffId)
                .then()
                .statusCode(is(200))
                .body("id", equalTo(staffId));
    }

    @Test
    public void shouldSuccessListStaffs() throws Exception {
        given()
                .header("Content-Type", "application/json")
                .param("page_size", "20")
                .param("page_index", "0")
                .param("sort_type", SortType.ASC)
                .param("sort_by", "timeCreated")
                .when()
                .get("staffs")
                .then()
                .statusCode(is(200))
                .body("totalCount", equalTo(1));
    }

    @Test
    public void shouldSuccessCreateStaffs() throws Exception {
        CreateStaffCommand command = new CreateStaffCommand();
        command.setName("test");
        command.setAuthorizations(new ArrayList<>());

        given()
                .header("Content-Type", "application/json")
                .body(command)
                .when()
                .post("staffs")
                .then()
                .statusCode(is(201));
    }
}
