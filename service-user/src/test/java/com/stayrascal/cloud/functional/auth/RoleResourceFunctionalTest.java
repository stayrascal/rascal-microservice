package com.stayrascal.cloud.functional.auth;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.stayrascal.cloud.common.contract.enumeration.SortType;
import com.stayrascal.cloud.functional.BaseFunctionalTest;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import com.stayrscal.cloud.user.auth.contract.command.CreateRoleCommand;
import com.stayrscal.cloud.user.auth.contract.command.CreateRolePermissionCommand;
import org.junit.Test;

@DatabaseSetups({
        @DatabaseSetup(value = "classpath:auth/roleResourceTest.xml", type = DatabaseOperation.TRUNCATE_TABLE),
        @DatabaseSetup(value = "classpath:auth/roleResourceTest.xml")
})
public class RoleResourceFunctionalTest extends BaseFunctionalTest {
    @Test
    public void shouldSuccessCreateRole() throws Exception {
        CreateRoleCommand command = new CreateRoleCommand();
        command.setName("test");

        given().header("Content-Type", "application/json")
                .body(command)
                .when()
                .post("roles")
                .then()
                .statusCode(is(201));
    }

    @Test
    public void shouldSuccessGetRole() throws Exception {
        String roleId = "role_id";

        given().header("Content-Type", "application/json")
                .when()
                .get("roles/" + roleId)
                .then()
                .statusCode(is(200))
                .body("name", equalTo("role_name"))
                .body("permissions[0].permissionType", equalTo("CREATE_STORE"));
    }

    @Test
    public void shouldSuccessListRoles() throws Exception {
        given().header("Content-Type", "application/json")
                .param("page_size", 10)
                .param("page_index", 0)
                .param("sort_type", SortType.ASC)
                .param("sort_by", "timeCreated")
                .param("name", "role")
                .when()
                .get("roles")
                .then()
                .statusCode(is(200))
                .body("totalCount", equalTo(1))
                .body("items[0].id", equalTo("role_id"));
    }

    @Test
    public void shouldSuccessCreateRolePermission() throws Exception {
        CreateRolePermissionCommand command = new CreateRolePermissionCommand();
        command.setPermissionType("CREATE_STORE");
        String roleId = "role_id";

        given().header("Content-Type", "application/json")
                .body(command)
                .when()
                .post("roles/" + roleId + "/permissions")
                .then()
                .statusCode(is(201));
    }

    @Test
    public void shouldSuccessRemoveRolePermission() throws Exception {
        String roleId = "role_id";
        Long permissionId = 1L;

        given().header("Content-Type", "application/json")
                .when()
                .delete("roles/" + roleId + "/permissions/" + permissionId)
                .then()
                .statusCode(is(204));
    }
}
