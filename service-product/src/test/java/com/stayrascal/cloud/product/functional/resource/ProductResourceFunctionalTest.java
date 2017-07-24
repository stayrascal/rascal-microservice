package com.stayrascal.cloud.product.functional.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.stayrascal.cloud.product.contract.command.UpdateProductInfoCommand;

import com.exmertec.dummie.Dummie;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Test;

@DatabaseSetup("classpath:ProductResourceFunctionalTest.xml")
@DatabaseTearDown("classpath:ProductResourceFunctionalTest.xml")
public class ProductResourceFunctionalTest extends BaseFunctionalTest {
    private static final String USER_ID = "497f60a8-93ad-4f47-a54d-e99d651fcb4e";

    @Test
    public void should_success_get_product_by_product_id() throws Exception {
        given()
                .header("Content-Type", "application/json")
                .when()
                .get("products/" + USER_ID)
                .then()
                .statusCode(is(200))
                .body("id", equalTo(USER_ID))
                .body("nickname", equalTo("name"))
                .body("email", equalTo("xxx@gg"))
                .body("mobile", equalTo("134567"));
    }

    @Test
    public void should_throw_404_when_get_product_with_invalid_product_id() throws Exception {
        String invalidProductId = "invalidProductId";
        given()
                .header("Content-Type", "application/json")
                .when()
                .get("products/" + invalidProductId)
                .then()
                .statusCode(is(404));
    }

    @Test
    public void should_success_update_product_info() throws Exception {
        UpdateProductInfoCommand command = Dummie.create(UpdateProductInfoCommand.class);
        given()
                .header("Content-Type", "application/json")
                .body(command)
                .when()
                .put("products/" + USER_ID)
                .then()
                .statusCode(is(200));
    }
}
