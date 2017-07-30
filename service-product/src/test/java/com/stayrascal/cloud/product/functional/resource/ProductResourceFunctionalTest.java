package com.stayrascal.cloud.product.functional.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.stayrascal.cloud.product.contract.command.UpdateProductCommand;

import com.exmertec.dummie.Dummie;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Test;

@DatabaseSetup("classpath:ProductResourceFunctionalTest.xml")
@DatabaseTearDown("classpath:ProductResourceFunctionalTest.xml")
public class ProductResourceFunctionalTest extends BaseFunctionalTest {
    private static final String PRODUCT_ID = "497f60a8-93ad-4f47-a54d-e99d651fcb4e";

    @Test
    public void shouldSuccessGetProductByProductId() throws Exception {
        given()
                .header("Content-Type", "application/json")
                .when()
                .get("products/" + PRODUCT_ID)
                .then()
                .statusCode(is(200))
                .body("id", equalTo(PRODUCT_ID));
    }

    @Test
    public void shouldThrow404WhenGetProductWithInvalidProductId() throws Exception {
        String invalidProductId = "invalidProductId";
        given()
                .header("Content-Type", "application/json")
                .when()
                .get("products/" + invalidProductId)
                .then()
                .statusCode(is(404));
    }

    @Test
    public void shouldSuccessUpdateProductInfo() throws Exception {
        UpdateProductCommand command = Dummie.create(UpdateProductCommand.class);
        given()
                .header("Content-Type", "application/json")
                .body(command)
                .when()
                .put("products/" + PRODUCT_ID)
                .then()
                .statusCode(is(200));
    }
}
